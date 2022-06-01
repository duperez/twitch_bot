package com.stream.bot.objects.stream.TwitchApiResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StreamDiferences {
    String objectChange;
    String oldValue;
    String newValue;
}