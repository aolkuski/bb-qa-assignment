package com.backbase.bblog.uitests.testutils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final String REST_API_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    private static final String UI_SINGLE_ARTICLE_TIME_FORMAT = "MMMM dd, yyyy";

    public static String getCurrentDateInSimpleFormat() {
        DateTimeFormatter singleArticleDateFormat = DateTimeFormatter.ofPattern(UI_SINGLE_ARTICLE_TIME_FORMAT);
        return getDate(singleArticleDateFormat);
    }

    public static String getDate(DateTimeFormatter dateFormat) {
        return LocalDateTime.now().format(dateFormat);
    }

    public static ZonedDateTime getZonedDateTimeFromString(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(REST_API_TIME_FORMAT);
        return ZonedDateTime.parse(input, formatter);
    }
}
