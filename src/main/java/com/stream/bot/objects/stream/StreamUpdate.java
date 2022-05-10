package com.stream.bot.objects.stream;

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

    public StreamUpdate(){
        streamDiferencesList = new ArrayList<>();
    }
}
