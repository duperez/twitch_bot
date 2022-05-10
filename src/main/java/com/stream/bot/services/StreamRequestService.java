package com.stream.bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.stream.bot.objects.stream.Stream;
import com.stream.bot.objects.stream.StreamList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Configuration("stream")
@Service
public class StreamRequestService {

     @Value("${auth}")
     String auth;

     @Value("${client_id}")
     String client_id;

     private StreamList getStreamStatus(String streamer) throws IOException{
          OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
                  .url("https://api.twitch.tv/helix/streams?user_login=" + streamer)
                  .header("Authorization", auth)
                  .header("Client-Id", client_id)
                  .build();
          Response response = client.newCall(request).execute();
          String stringResponse = response.body().string();
          return new ObjectMapper().readValue(stringResponse, StreamList.class);
     }


     public Stream getStream(String streamer) throws IOException {
          StreamList list = getStreamStatus(streamer);
          boolean listIsNotEmpty = !list.getStreams().isEmpty();
          Stream stream;
          if (listIsNotEmpty) {
               stream = list.getStreams().get(0);
               stream.setStatus(true);
          } else {
               stream = new Stream("", "", false);
               stream.setUserName(streamer);
          }
          return stream;
     }
}
