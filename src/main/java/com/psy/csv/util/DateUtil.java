package com.psy.csv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtil {

    public static String pattern = "yyyy-MM-dd";

    /**
     * get time from string with pattern
     * or parse string to long and return new instance
     * or Date NOW!
     *
     * @param str - string to parce
     * @return Date obj
     */
    public static Date getDateFromString(String str) {
        try {
            return new SimpleDateFormat(pattern).parse(str);
        } catch (ParseException pe) {
            try {
                return new Date(Long.parseLong(str));
            } catch (NumberFormatException nfe) {
                return Date.from(Instant.now());
            }

        }
    }


}
