package com.stream.bot.services;

import com.stream.bot.objects.stream.StreamUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PublishService {

    @Autowired
    StreamUpdateService streamUpdateService;

    public StreamUpdate shareUpdates(String stream) throws IOException {
        StreamUpdate streamUpdate = streamUpdateService.getUpdates(stream);
        new TwitterService().publishUpdate(streamUpdate);
        return streamUpdate;
    }

}
