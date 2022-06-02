package com.stream.bot.services;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.dto.StreamDto;
import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import com.stream.bot.objects.stream.TwitchApiResponses.StreamCompare;
import com.stream.bot.objects.stream.TwitchApiResponses.StreamUpdate;
import com.stream.bot.repositories.StreamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PublishService {

    @Autowired
    TwitterService twitterService;

    @Autowired
    StreamRequestService service;

    @Autowired
    StreamRepository streamRepository;

    public void shareUpdates(String stream) throws IOException {
        StatusDto streamValue = new StatusDto(getStream(stream));
        StreamDto streamDto = getLastUpdate(stream);
        if (!streamDto.getStatusDtoList().isEmpty()) {
            log.info("checking for updates");
            if (!streamDto.getStatusDtoList().get(streamDto.getStatusDtoList().size() - 1).equals(streamValue)) {
                streamDto.addNewStatus(streamValue);
                streamRepository.save(streamDto);
            }
        } else {
            log.info("there is no previous data, saving new data");
            streamDto.addNewStatus(streamValue);
            streamDto.setStreamer(stream);
            streamRepository.save(streamDto);
        }
    }

    public StreamDto getLastUpdate(String stream) {
        List<StreamDto> lastUpdateDto = streamRepository.getLastUpdate(stream, PageRequest.of(0,1));
        if (!lastUpdateDto.isEmpty() && lastUpdateDto.get(0).getStatus() != null  && lastUpdateDto.get(0).getStatus().equals("ONLINE")) {
            return lastUpdateDto.get(0);
        }
        return new StreamDto();
    }

    public Stream getStream(String streamer) throws IOException {
        Stream stream = service.getStream(streamer);
        stream.setTitle(stream.getTitle());
        return stream;
    }

}
