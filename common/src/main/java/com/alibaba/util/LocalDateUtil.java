package com.alibaba.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * LocalDate
 */
public class LocalDateUtil {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static LocalDateTime getNowDate() {
        return LocalDateTime.now();
    }

    /**
     * 格式化日期
     *
     * @param dateTime 日期
     * @param format   格式化
     * @return 返回值 String
     */
    public static LocalDateTime getFormat(LocalDateTime dateTime, String format) {
        if (dateTime != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
            String format1 = dateTime.format(dtf);
            return LocalDateTime.parse(format1, dtf);
        }
        return null;
    }

    /**
     * 计算日期差
     */
    public static Map<String, Object> between(LocalTime first, LocalTime second) {
        int firstHour = first.getHour();
        int firstMinute = first.getMinute();
        int firstSecond = first.getSecond();

        int secondHour = second.getHour();
        int secondMinute = second.getMinute();
        int secondSecond = second.getSecond();

        LocalTime firstTime = LocalTime.of(firstHour, firstMinute, firstSecond);
        LocalTime secondTime = LocalTime.of(secondHour, secondMinute, secondSecond);

        Duration between = Duration.between(firstTime, secondTime);
        long day = between.toDays();
        long hours = between.toHours();
        long minutes = between.toMinutes();
        long nanos = between.toNanos();
        long seconds = between.getSeconds();
        Map<String, Object> map = new HashMap<>();
        map.put("hours", hours);
        map.put("minutes", minutes);
        map.put("nanos", nanos);
        map.put("seconds", seconds);
        return map;
    }

    public static void main(String[] args) {
        LocalDateTime nowDate = LocalDateUtil.getNowDate();

        LocalDateTime format = LocalDateUtil.getFormat(nowDate, DATE_TIME_PATTERN);
        System.out.println(format);

        LocalTime time = LocalTime.of(20, 00, 00);
        Map<String, Object> between = LocalDateUtil.between(LocalTime.now(), time);
        System.out.println(between);
    }
}
