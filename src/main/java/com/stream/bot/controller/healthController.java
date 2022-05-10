package com.stream.bot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@EnableScheduling
@Slf4j
public class healthController {

    @GetMapping("ping")
    public String ping(){
        Locale brasil = new Locale("pt", "EUA");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);

        log.info("starting new run: {}", dateFormat.format(new Date()));
        return "pong";
    }
}
