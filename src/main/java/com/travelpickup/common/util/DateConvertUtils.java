package com.travelpickup.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConvertUtils {

    public final static String YYYY_DOT_MM_DOT_DD_PATTERN = "yyyy. MM. dd";

    public static String localDateConvert(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

}
