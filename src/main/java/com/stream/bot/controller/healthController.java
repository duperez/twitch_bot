package com.stream.bot.controller;

import com.stream.bot.services.interfaces.HealthServiceInterface;
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
    HealthServiceInterface healthServiceImp;

    @GetMapping("ping")
    public String ping(){

        return "pong";
    }

    @GetMapping("health/check")
    @Scheduled(fixedRate = 10000)
    public void autoSetter() throws IOException {
        log.info("checking health");
        healthServiceImp.checkHealth();
    }
}
