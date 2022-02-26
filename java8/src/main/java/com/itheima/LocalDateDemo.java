package com.itheima;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateDemo {

    public static void main(String[] args) {
        // LocalDate：表示日期，有年月日
        LocalDate now = LocalDate.now();
        System.out.println("当前日期：" + now);
        System.out.println("得到当前：" + now.getYear() + "年" + now.getMonth() + "(" + now.getMonthValue() + ")" + "月" + now.getDayOfMonth() + "日");

        LocalDate of = LocalDate.of(2020, 02, 10);
        System.out.println("指定日期：" + of);

        // LocalTime：表示时间，有时分秒
        LocalTime time = LocalTime.now();
        System.out.println("当前时间：" + time);
        System.out.println("得到当前：" + time.getHour() + "时" + time.getMinute() + "分" + time.getSecond() + "秒" + time.getNano());

        LocalTime localTime = LocalTime.of(13, 10, 20);
        System.out.println("指定时间：" + localTime);

        // LocalDateTime：表示日期时间，有年月日时分秒
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println("当前日期和时间：" + now1);
        LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 12, 00, 00);
        System.out.println("指定日期和时间：" + dateTime);
        System.out.println("得到当前：" + now1.getYear() + "年"
                + now1.getMonthValue() + "月"
                + now1.getDayOfMonth() + "日"
                + now1.getHour() + "时"
                + now1.getMinute() + "分"
                + now1.getSecond() + "秒"
                + now1.getNano());

        // 日期修改
        LocalDateTime now2 = LocalDateTime.now();
        // 修改时间，修改后返回新的时间对象
        LocalDateTime dateTime1 = now2.withYear(2100);
        System.out.println("dateTime1:" + dateTime1);
        System.out.println("dateTime1==now2:" + (dateTime1 == now2));

        // 日期加减
        // plus：加指定时间
        // miuns：减指定的时间
        LocalDateTime localDateTime = now2.plusYears(2);
        System.out.println("加两年："+localDateTime);
        LocalDateTime localDateTime2 = now2.minusYears(2);
        System.out.println("减两年："+localDateTime2);
        LocalDateTime localDateTime1 = now2.plusDays(2);
        System.out.println("加两天："+localDateTime1);
        LocalDateTime localDateTime3 = now2.minusDays(2);
        System.out.println("减两天："+localDateTime3);
    }
}
