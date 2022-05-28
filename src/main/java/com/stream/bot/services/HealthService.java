package com.stream.bot.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HealthService {


    public void checkHealth() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://duperez-twitch-bot.herokuapp.com/ping")
                .build();
        Response response = client.newCall(request).execute();
    }
}
