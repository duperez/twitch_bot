package com.stream.bot.controller;


import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import com.stream.bot.services.StreamRequestService;
import com.stream.bot.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class StreamController {

    @Autowired
    StreamRequestService streamRequestService;

    @GetMapping("stream/status")
    public Stream streamTwitch(@RequestParam String streamerName) throws IOException {

        log.info("starting new run: {}", DateUtils.getLocalDate());
        return streamRequestService.getStream(streamerName);
    }
}
