package com.stream.bot.services;

import com.stream.bot.objects.stream.Stream;
import com.stream.bot.objects.stream.StreamCompare;
import com.stream.bot.objects.stream.StreamUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Slf4j
@Service
public class StreamUpdateService {

    @Autowired
    StreamRequestService service;

    Stream actualStream = null;


public StreamUpdate getUpdates(String streamer) throws IOException {
    Stream stream = service.getStream(streamer);
    StreamUpdate streamUpdate = new StreamUpdate();
    stream.setTitle(stream.getTitle());
    if (actualStream != null){
        log.info("checking for new updates");
        streamUpdate = StreamCompare.getUpdates(actualStream, stream);
        streamUpdate.setStreamerName(streamer);
    } else {
        log.info("Application is starting, setting stream values");
    }
    actualStream = stream;

    return streamUpdate;
}
}
