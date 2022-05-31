package com.stream.bot.services;

import com.stream.bot.objects.dto.StreamDto;
import com.stream.bot.objects.stream.Stream;
import com.stream.bot.objects.stream.StreamCompare;
import com.stream.bot.objects.stream.StreamUpdate;
import com.stream.bot.repositories.StreamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.io.IOException;
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

    StreamDto streamDto = null;

    public void shareUpdates(String stream) throws IOException {
        StreamDto streamValue = new StreamDto(getStream(stream));
        StreamDto streamDto = getLastUpdate(stream);
        if (streamDto != null) {
            log.info("checking for updates");
            StreamUpdate streamUpdate = StreamCompare.getUpdates(streamDto, streamValue);
            streamUpdate.setStreamerName(streamValue.getStreamer());
            if (!streamUpdate.getStreamDiferencesList().isEmpty()) {
                streamRepository.save(streamDto);
            }
        } else {
            streamRepository.save(streamValue);
        }
    }

    public StreamDto getLastUpdate(String stream) {
        List<StreamDto> lastUpdateDto = streamRepository.getLastUpdate(stream, PageRequest.of(0,1));
        if (!lastUpdateDto.isEmpty()) {
            return lastUpdateDto.get(0);
        }
        return null;
    }

    public Stream getStream(String streamer) throws IOException {
        Stream stream = service.getStream(streamer);
        stream.setTitle(stream.getTitle());
        return stream;
    }

}
