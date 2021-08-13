package com.backbase.bblog.testutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static String getCurrentDateInSimpleFormat() {
        DateTimeFormatter singleArticleDateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return getDate(singleArticleDateFormat);
    }

    public static String getDate(DateTimeFormatter dateFormat) {
        return LocalDateTime.now().format(dateFormat);
    }
}
