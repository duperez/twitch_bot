package com.stream.bot.controller;

import com.stream.bot.objects.stream.TwitchApiResponses.StreamDiferences;
import com.stream.bot.objects.stream.TwitchApiResponses.StreamUpdate;
import com.stream.bot.services.interfaces.PublishServiceInterface;
import com.stream.bot.services.interfaces.TwitterServiceInterface;
import com.stream.bot.utils.DateUtils;
import com.stream.bot.utils.TwiiterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.*;

@Slf4j
@EnableScheduling
@RestController
public class StreamPublishingController {

    @Autowired
    PublishServiceInterface publishService;

    @Autowired
    TwitterServiceInterface twitterService;

    @GetMapping("stream/autoPublish")
    @Scheduled(fixedRate = 10000)
    public void streamTwitch() throws IOException, TwitterException {
        publishService.shareUpdates("Cellbit");
    }
    @GetMapping("stream/Manualpublish")
    public void publish(@RequestBody StreamDiferences update, @RequestParam String streamerName) throws TwitterException {

        log.info("starting new run: {}", DateUtils.getLocalDate());

        StreamUpdate streamUpdate = new StreamUpdate();

        List<StreamDiferences> list = new ArrayList<>();
        list.add(update);

        streamUpdate.setStreamDiferencesList(list);

        streamUpdate.setStreamerName(streamerName);

        twitterService.publishUpdate(TwiiterUtils.createChangeMessage(streamUpdate));
    }


}
