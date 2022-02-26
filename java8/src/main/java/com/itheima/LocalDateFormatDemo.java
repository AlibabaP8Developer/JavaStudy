package com.itheima;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatDemo {
    /**
     * 日期格式化
     * @param args
     */
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 格式化，指定时间格式
        // JDK自带的日期时间格式:DateTimeFormatter.ISO_DATE_TIME
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(dtf);
        System.out.println(format);

        // 解析
        LocalDateTime parse = LocalDateTime.parse("2022-02-26 22:51:18", dtf);
        System.out.println(parse);
    }
}
