package com.stream.bot.objects.stream.TwitchApiResponses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StreamList {

    @JsonProperty("data")
    List<Stream> streams;

    @JsonIgnore
    @JsonProperty("pagination")
    List<String> pagination;
}
