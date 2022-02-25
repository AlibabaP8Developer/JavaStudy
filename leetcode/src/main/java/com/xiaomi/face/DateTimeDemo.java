package com.xiaomi.face;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
     java.util.Date 既包含日期又包含时间，而 java.time 把它们进行了分离
     LocalDateTime.class //日期+时间 format: yyyy-MM-ddTHH:mm:ss.SSS
     LocalDate.class //日期 format: yyyy-MM-dd
     LocalTime.class //时间 format: HH:mm:ss
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        LocalTime time = LocalTime.now();
        System.out.println(String.format("time format: %s", time));
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTime.format(dateTimeFormatter);
        System.out.println(String.format("dateTime format : %s", dateTimeStr));
    }

}
