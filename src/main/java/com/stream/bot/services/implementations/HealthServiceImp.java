package com.stream.bot.services.implementations;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.stream.bot.services.interfaces.HealthServiceInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HealthServiceImp implements HealthServiceInterface {


    public void checkHealth() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://duperez-twitch-bot.herokuapp.com/ping")
                .build();
        Response response = client.newCall(request).execute();
    }
}
