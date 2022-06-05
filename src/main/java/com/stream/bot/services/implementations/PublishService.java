package com.stream.bot.services.implementations;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.dto.StreamDto;
import com.stream.bot.objects.model.StreamModel;
import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import com.stream.bot.repositories.StreamRepository;
import com.stream.bot.services.interfaces.PublishServiceInterface;
import com.stream.bot.services.interfaces.StreamRequestServiceInterface;
import com.stream.bot.services.interfaces.TwitterServiceInterface;
import com.stream.bot.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class PublishService implements PublishServiceInterface {

    @Autowired
    TwitterServiceInterface twitterService;

    @Autowired
    StreamRequestServiceInterface service;

    @Autowired
    StreamRepository streamRepository;

    public void shareUpdates(String stream) throws IOException {
        StatusDto streamValue = new StatusDto(getStream(stream));
        StreamDto streamDto = getLastUpdate(stream);
        if (!streamDto.getStatusModelList().isEmpty()) {
            log.info("checking for updates");
            if (!streamDto.getStatusModelList().get(streamDto.getStatusModelList().size() - 1).equals(streamValue)) {
                log.info("updated found");
                streamValue.setDate(DateUtils.getLocalDate());
                streamDto.addNewStatus(streamValue);
                streamRepository.saveAndFlush(new StreamModel(streamDto));
            } else {
                log.info("there is no new update");
            }
        } else {
            log.info("there is no previous data, checking if need to save new data");
            if (streamValue.getStatus()) {
                streamValue.setDate(DateUtils.getLocalDate());
                streamDto.addNewStatus(streamValue);
                streamDto.setStreamer(stream);
                streamRepository.save(new StreamModel(streamDto));
            }
        }
    }

    public StreamDto getLastUpdate(String stream) {
        List<StreamModel> lastUpdateDto = streamRepository.getLastUpdate(stream, PageRequest.of(0,1));
        if (!lastUpdateDto.isEmpty() && lastUpdateDto.get(0).getStatus() != null  && lastUpdateDto.get(0).getStatus().equals("ONLINE")) {
            return new StreamDto(lastUpdateDto.get(0));
        }
        return new StreamDto();
    }

    public Stream getStream(String streamer) throws IOException {
        Stream stream = service.getStream(streamer);
        stream.setTitle(stream.getTitle());
        return stream;
    }

}
