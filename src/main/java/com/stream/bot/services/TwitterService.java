package com.stream.bot.services;

import com.stream.bot.objects.stream.StreamDiferences;
import com.stream.bot.objects.stream.StreamUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
@Configuration("twitter")
public class TwitterService {

    @Value("${access_token}")
    String accessToken;
    @Value("${access_token_scret}")
    String accessTokenSecret;
    @Value("${consumer_key}")
    String consumerKey;
    @Value("${consumer_secret}")
    String consumerSecret;

    public void publishUpdate(StreamUpdate updates) {
        updates.getStreamDiferencesList().forEach(difference ->
        {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            try {
                twitter.updateStatus(getString(difference));
            } catch (TwitterException e) {
                log.info("Wanst able to login or tweet, error {}", e.getErrorMessage());
            }
            log.info("setting new stream difference {}", getString(difference));
            System.out.println(getString(difference));

        });
    }

    public static String getString(StreamDiferences update) {
        String message = "";
        switch (update.getObjectChange()) {
            case "game":
                message =  "Cellbit agora esta jogando: " + update.getNewValue();
                break;
            case "title":
                message =  "Novo titulo da live do Cellbit: " + update.getNewValue();
                break;
            case "status":
                message =  "Cellbit agora esta: " + (update.getNewValue().equals("true") ? "online" : "offilne");
                break;
        }
        Locale brasil = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        String date = dateFormat.format(dateFormat.format(new Date()));
        return message + "\nas " + date;
    }



}