package com.stream.bot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import twitter4j.ManageTweetsExKt;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Slf4j
@Configuration("twitter")
@Service
public class TwitterService {

    @Value("${consumer_key}")
    String consumerKey;
    @Value("${consumer_secret}")
    String consumerSecret;
    @Value("${access_token}")
    String accessToken;
    @Value("${access_token_scret}")
    String accessTokenSecret;

    public void publishUpdate(String update) throws TwitterException {


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        ManageTweetsExKt.createTweet(twitter,null, null, null, null, null, null, null, null, null, null, null, update);
    }



}