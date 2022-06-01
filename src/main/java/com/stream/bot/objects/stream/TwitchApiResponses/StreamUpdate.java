package com.stream.bot.objects.stream.TwitchApiResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class StreamUpdate {

    List<StreamDiferences> streamDiferencesList;
    String changeDate;
    String streamerName;


    public StreamUpdate(){
        streamDiferencesList = new ArrayList<>();
    }
}
