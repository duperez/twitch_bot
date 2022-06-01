package com.stream.bot.utils;

import com.stream.bot.objects.stream.TwitchApiResponses.StreamDiferences;
import com.stream.bot.objects.stream.TwitchApiResponses.StreamUpdate;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.stream.Collectors;

public class TwiiterUtils {

    private static String createChangeMessage(StreamDiferences update) {
        String message = "";
        switch (update.getObjectChange()) {
            case "game":
                message =  "agora esta jogando: " + update.getNewValue();
                break;
            case "title":
                message =  "Novo titulo da live: " + update.getNewValue();
                break;
            case "status":
                message =  "Esta: " + (update.getNewValue().equals("true") ? "online" : "offilne");
                break;
        }

        return message;
    }

    public static String createChangeMessage(StreamUpdate updates) {
        String values = StringUtils.join(updates.getStreamDiferencesList().stream().map(TwiiterUtils::createChangeMessage).collect(Collectors.toList()), '\n');
        return "Update na live do " + updates.getStreamerName() + "\n" + values + "\natualizacao " + DateUtils.getLocalDate();
    }

}
