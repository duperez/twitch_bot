package com.stream.bot.controller;

import com.stream.bot.objects.stream.StreamUpdate;
import com.stream.bot.services.PublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
@EnableScheduling
@RestController
public class StreamPublishingController {

    @Autowired
    PublishService publishService;

    @GetMapping("stream/autoPublish")
    @Scheduled(fixedRate = 5000)
    public StreamUpdate streamTwitch() throws IOException {
        Locale brasil = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        log.info("starting new run: {}", dateFormat.format(new Date()));
        return publishService.shareUpdates("Cellbit");
    }


}
