package com.xiaomi.localdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class A01ZoneIdDemo2 {

    public static void main(String[] args) {
        /*
            public LocalDate toLocalDate()  LocalDateTime转换成一个LocalDate对象
            public LocalTime toLocalTime()  LocalDateTime转换成一个LocalTime对象
            LocalDateTime
            static xxx now()  获取当前时间的对象
            static xxx of()  获取指定时间的对象
            get开头的方法   获取日历中的年月日时分秒等信息
            isBefore，isAfter  比较两个LocalDate
            with开头的  修改时间系列的ff
            minus开头的   减少时间系列的方法
            plus开头的   增加时间系列的方法
         */

        // 获取当前时间的日历对象（包含年月日）
        LocalDate localDate = LocalDate.now();
        System.out.println("今天的日期：" + localDate);

        // 获取指定的时间的日历对象
        LocalDate ldDate = LocalDate.of(2023, 1, 1);
        System.out.println("指定日期：" + ldDate);

        // get系列方法获取日历中的每一个属性值
        int year = ldDate.getYear();
        System.out.println("year：" + year);

        // 获取月
        // 1
        Month month = ldDate.getMonth();
        System.out.println("1 - month：" + month);
        System.out.println("1- month：" + month.getValue());

        // 2
        int monthValue = ldDate.getMonthValue();
        System.out.println("2 - month:" + monthValue);

        // 获取日
        int day = ldDate.getDayOfMonth();
        System.out.println("day：" + day);

        // 获取一年的第几天
        int dayOfYear = ldDate.getDayOfYear();
        System.out.println("获取一年的第几天 dayOfYear：" + dayOfYear);

        // 获取星期
        DayOfWeek dayOfWeek = ldDate.getDayOfWeek();
        System.out.println("获取星期 dayOfWeek："+dayOfWeek);
        System.out.println(dayOfWeek.getValue());

        System.out.println(ldDate.isBefore(ldDate));
        System.out.println(ldDate.isAfter(ldDate));

        // with开头的方法表示修改，只能修改年月日
        LocalDate withYear = ldDate.withYear(2000);
        System.out.println(withYear);

        // minux开头的方法表示减少，只能减少年月日
        LocalDate minusYears = ldDate.minusYears(1);
        System.out.println(minusYears);

        // plus开头的方法表示增加，只能增加年月日
        LocalDate plusDays = ldDate.plusDays(1);
        System.out.println(plusDays);
    }
}
