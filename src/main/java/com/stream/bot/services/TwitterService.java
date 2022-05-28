package com.stream.bot.services;

import com.stream.bot.objects.stream.StreamDiferences;
import com.stream.bot.objects.stream.StreamUpdate;
import com.stream.bot.utils.DateUtils;
import com.stream.bot.utils.TwiiterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import twitter4j.ManageTweetsExKt;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

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