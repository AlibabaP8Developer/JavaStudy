package com.alibaba.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public static String getFormat(LocalDateTime dateTime, String format) {
        if (dateTime != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
            return dateTime.format(dtf);
        }
        return null;
    }

    public static void main(String[] args) {
        LocalDateTime nowDate = LocalDateUtil.getNowDate();
        String format = LocalDateUtil.getFormat(nowDate, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
    }
}
