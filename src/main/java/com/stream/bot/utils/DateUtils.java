package com.stream.bot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static String getLocalDate() {
        Locale brasil = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", brasil);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        return dateFormat.format(new Date());
    }


}
