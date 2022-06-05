package com.stream.bot.services.interfaces;

import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

public interface TwitterServiceInterface {

    void publishUpdate(String update) throws TwitterException;

}
