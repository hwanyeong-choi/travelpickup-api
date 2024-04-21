package com.travelpickup.common.utils

import com.travelpickup.common.util.DateConvertUtils
import spock.lang.Specification

import java.time.LocalDateTime

class DateConvertUtilsTests extends Specification {

    def "DateConvertUtils localDateConvert메소드가 yyyy. MM. dd 형식의 문자열을 리턴하는가"(LocalDateTime currentLocalDateTime, String currentLocalDateTimeByStr) {

        expect:
        DateConvertUtils.localDateConvert(currentLocalDateTime, DateConvertUtils.YYYY_DOT_MM_DOT_DD_PATTERN) == currentLocalDateTimeByStr

        where:
        currentLocalDateTime                    |   currentLocalDateTimeByStr
        LocalDateTime.of(2024, 4, 20, 0, 0, 0)  |   "2024. 04. 20"
        LocalDateTime.of(2023, 1, 20, 0, 0, 0)  |   "2023. 01. 20"
        LocalDateTime.of(2020, 4, 20, 0, 0, 0)  |   "2020. 04. 20"
        LocalDateTime.of(2020, 4, 21, 0, 0, 0)  |   "2020. 04. 21"

    }

}
