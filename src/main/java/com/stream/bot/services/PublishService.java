package com.stream.bot.services;

import com.stream.bot.objects.stream.StreamUpdate;
import com.stream.bot.utils.TwiiterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.io.IOException;

@Service
@Slf4j
public class PublishService {

    @Autowired
    StreamUpdateService streamUpdateService;
    @Autowired
    TwitterService twitterService;

    public StreamUpdate shareUpdates(String stream) throws IOException, TwitterException {
        StreamUpdate streamUpdate = streamUpdateService.getUpdates(stream);
        if (!streamUpdate.getStreamDiferencesList().isEmpty()) {
            log.info("Found updates in the live status");
            twitterService.publishUpdate(TwiiterUtils.createChangeMessage(streamUpdate));
        }
        return streamUpdate;
    }
}
