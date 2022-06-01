package com.stream.bot.services;

import com.stream.bot.objects.dto.StatusDto;
import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import com.stream.bot.objects.stream.TwitchApiResponses.StreamUpdate;
import com.stream.bot.repositories.StreamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Slf4j
@Service
public class StreamUpdateService {

    @Autowired
    StreamRequestService service;

    @Autowired
    StreamRepository streamRepository;

    Stream actualStream = null;



    public StreamUpdate getUpdates(String streamer) throws IOException {
        Stream stream = service.getStream(streamer);
        StreamUpdate streamUpdate = new StreamUpdate();
        stream.setTitle(stream.getTitle());
        if (actualStream != null){
            log.info("checking for new updates");
            //streamUpdate = StreamCompare.getUpdates(actualStream, stream);
            streamUpdate.setStreamerName(streamer);
        } else {
            log.info("Application is starting, setting stream values");
        }
        actualStream = stream;

        return streamUpdate;
    }
    public StreamUpdate getUpdates(Stream stream) throws IOException {
        StreamUpdate streamUpdate = new StreamUpdate();
        if (actualStream != null){
            log.info("checking for new updates");
            //streamUpdate = StreamCompare.getUpdates(actualStream, stream);
            streamUpdate.setStreamerName(stream.getUserName());
        } else {
            log.info("Application is starting, setting stream values");
            //streamRepository.save(new StatusDto(stream));
        }
        actualStream = stream;

        return streamUpdate;
    }
}
