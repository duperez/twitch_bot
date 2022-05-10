package com.stream.bot.objects.stream;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class StreamCompare {

    public static StreamUpdate getUpdates(Stream oldStream, Stream newStream){
        List<StreamDiferences> streamDiferences = new ArrayList<>();

        log.info("Old game: {}", oldStream.getGameName());
        log.info("New game: {}", newStream.getGameName());
        log.info("----------------------------");
        log.info("Old title: {}", oldStream.getTitle());
        log.info("New title: {}", newStream.getTitle());
        log.info("----------------------------");
        log.info("Old status: {}", oldStream.getStatus());
        log.info("New status: {}", newStream.getStatus());
        log.info("----------------------------");
        StreamDiferences gameStreamDiferences = null;
        StreamDiferences titleStreamDiferences = null;
        StreamDiferences statusStreamDiferences = null;
        if (!Objects.equals(oldStream.getGameName(), newStream.getGameName())) {
            log.info("setting new game");
            gameStreamDiferences = new StreamDiferences();
            gameStreamDiferences.setObjectChange("game");
            gameStreamDiferences.setOldValue(oldStream.getGameName());
            gameStreamDiferences.setNewValue(newStream.getGameName());
        }
        if (!Objects.equals(oldStream.getTitle(), newStream.getTitle())) {
            log.info("setting new title");
            titleStreamDiferences = new StreamDiferences();
            titleStreamDiferences.setObjectChange("title");
            titleStreamDiferences.setOldValue(oldStream.getTitle());
            titleStreamDiferences.setNewValue(newStream.getTitle());
        }
        if (oldStream.getStatus() != oldStream.getStatus()) {
            log.info("setting new status");
            statusStreamDiferences = new StreamDiferences();
            statusStreamDiferences.setObjectChange("status");
            statusStreamDiferences.setOldValue(oldStream.getStatus().toString());
            statusStreamDiferences.setNewValue(newStream.getStatus().toString());
        }


        if (gameStreamDiferences != null)
            streamDiferences.add(gameStreamDiferences);
        if (titleStreamDiferences != null)
            streamDiferences.add(titleStreamDiferences);
        if (statusStreamDiferences != null)
            streamDiferences.add(statusStreamDiferences);


        Locale brasil = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        return new StreamUpdate(streamDiferences, dateFormat.format(new Date()));
    }

}
