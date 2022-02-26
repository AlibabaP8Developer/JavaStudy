package com.itheima;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalDateDemo {

    public static void main(String[] args) {
        // LocalDate：表示日期，有年月日
        LocalDate now = LocalDate.now();
        System.out.println("当前日期：" + now);
        System.out.println("得到当前"+now.getYear()+"年"+now.getMonth()+"("+now.getMonthValue()+")"+"月"+now.getDayOfMonth()+"日");

        LocalDate of = LocalDate.of(2020, 02, 10);
        System.out.println("指定日期：" + of);

        // LocalTime：表示时间，有时分秒
        LocalTime time = LocalTime.now();
        System.out.println("当前时间："+time);
        System.out.println("得到当前"+time.getHour()+"时"+time.getMinute()+"分"+time.getSecond()+"秒"+time.getNano());

        LocalTime localTime = LocalTime.of(13, 10, 20);
        System.out.println("指定时间：" + localTime);
    }
}
