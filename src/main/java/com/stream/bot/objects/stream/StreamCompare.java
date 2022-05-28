package com.stream.bot.objects.stream;

import com.stream.bot.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class StreamCompare {

    public static StreamUpdate getUpdates(Stream oldStream, Stream newStream){
        List<StreamDiferences> streamDiferences = new ArrayList<>();

        if (oldStream.getStatus() != newStream.getStatus()) {
            log.info("updated {} from {} to {}", "status", oldStream.getStatus().toString(), newStream.getStatus().toString());
            streamDiferences.add(createNewDiference("status", oldStream.getStatus().toString(), newStream.getStatus().toString()));
        } else {
            log.info("keeping the same status {}", oldStream.getStatus().toString());
        }

        if (!Objects.equals(oldStream.getTitle(), newStream.getTitle()) && !Objects.equals(newStream.getTitle(), "")) {
            log.info("updated {} from {} to {}", "title", oldStream.getTitle(), newStream.getTitle());
            streamDiferences.add(createNewDiference("title", oldStream.getTitle(), newStream.getTitle()));
        } else {
            log.info("keeping the same status {}", oldStream.getTitle());
        }

        if (!Objects.equals(oldStream.getGameName(), newStream.getGameName()) && !Objects.equals(newStream.getGameName(), "")) {
            log.info("updated {} from {} to {}", "game", oldStream.getGameName(), newStream.getGameName());
            streamDiferences.add(createNewDiference("game", oldStream.getGameName(), newStream.getGameName()));
        } else {
            log.info("keeping the same game {}", oldStream.getGameName());
        }

        return new StreamUpdate(streamDiferences, DateUtils.getLocalDate() , "");
    }

    private static StreamDiferences createNewDiference(String itemChange, String oldValue, String newValue) {
        return new StreamDiferences(itemChange, oldValue, newValue);
    }


}
