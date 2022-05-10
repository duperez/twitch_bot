package com.stream.bot.controller;


import com.stream.bot.objects.stream.Stream;
import com.stream.bot.services.StreamRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
@RestController
public class StreamController {

    @Autowired
    StreamRequestService streamRequestService;

    @GetMapping("stream/status")
    public Stream streamTwitch(@RequestParam String streamerName) throws IOException {
        Locale brasil = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        log.info("starting new run: {}", dateFormat.format(new Date()));
        return streamRequestService.getStream(streamerName);
    }
}
