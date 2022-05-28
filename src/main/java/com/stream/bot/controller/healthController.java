package com.stream.bot.controller;

import com.stream.bot.services.HealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@EnableScheduling
@Slf4j
public class healthController {

    @Autowired
    HealthService healthService;

    @GetMapping("ping")
    public String ping(){

        return "pong";
    }

    @GetMapping("stream/autoPublish")
    @Scheduled(fixedRate = 10000)
    public void autoSetter() throws IOException {
        log.info("checking health");
        healthService.checkHealth();
    }
}
