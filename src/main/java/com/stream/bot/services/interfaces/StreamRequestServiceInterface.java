package com.stream.bot.services.interfaces;

import com.stream.bot.objects.stream.TwitchApiResponses.Stream;
import java.io.IOException;

public interface StreamRequestServiceInterface {

    Stream getStream(String streamer) throws IOException;

}
