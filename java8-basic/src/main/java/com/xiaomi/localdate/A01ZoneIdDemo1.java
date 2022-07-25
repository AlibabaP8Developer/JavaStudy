package com.xiaomi.localdate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class A01ZoneIdDemo1 {

    public static void main(String[] args) {
        // 获取所有的时区名称
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds.size());
        System.out.println(zoneIds);
        // 获取当前系统的默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);// Asia/Shanghai
        // 获取指定的时区
        ZoneId of = ZoneId.of("Africa/Nairobi");
        System.out.println(of);
        /*
            Instant时间戳
            static Instant now()  获取当前时间的Instant对象（标准时间）
            static Instant ofXxx()  根据（秒/毫秒/纳秒）获取Instant对象
            ZonedDateTime atZone(ZoneId zone)  指定时区
            boolean isXxx(Instant otherInstant)  判断系列的方法
            Instant minusXxx(long millisToSubtract)  减少时间系列的方法
            Instant plusXxx(long millisToSubtract)  增加时间系列的方法
         */

        Instant now = Instant.now();
        System.out.println(now);

        Instant instant = Instant.ofEpochMilli(0L);// 毫秒值
        System.out.println(instant);

        Instant instant1 = Instant.ofEpochSecond(1L);
        System.out.println(instant1);

        Instant instant2 = Instant.ofEpochSecond(1L, 1000000000L);
        System.out.println(instant2);

        // 指定时区
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);

        // 时间判断
        Instant instant3 = Instant.ofEpochMilli(0L);
        Instant instant4 = Instant.ofEpochMilli(1000L);
        System.out.println(instant4);
        boolean before = instant4.isBefore(instant3);
        boolean after = instant4.isAfter(instant3);
        System.out.println(before);
        System.out.println(after);

        Instant instant5 = instant4.minusSeconds(1L);
        System.out.println(instant5);

        // 获取当前时间对象（带时区）
        ZonedDateTime now1 = ZonedDateTime.now();
        System.out.println(now1);

        // 获取指定的时间对象（带时区）
        ZonedDateTime time = ZonedDateTime.of(2023, 10, 1, 11, 11, 11, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println(time);

        // 通过Instant + 时区的方式指定获取时间对象
        Instant instant6 = Instant.ofEpochMilli(0L);
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime time1 = ZonedDateTime.ofInstant(instant6, zoneId1);
        System.out.println(time1);

        // withxxx修改时间系列的方法
        ZonedDateTime time2 = time1.withYear(2000);
        System.out.println(time2);

        // 减少时间
        ZonedDateTime time3 = time2.minusYears(1);
        System.out.println(time3);

        // 增加时间
        ZonedDateTime time4 = time3.minusYears(1);
        System.out.println(time4);

        ZonedDateTime time5 = time4.plusYears(1);
        System.out.println(time5);

        /*
            日期格式化
            DateTimeFormatter 用于时间的格式化和解析
            static DateTimeFormatter ofPattern(格式)  获取格式对象
            String format(时间对象)  按照指定方式格式化
         */

        // 获取时间对象
        ZonedDateTime zone = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        // 解析/格式化器
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss EE a");
        // 格式化
        String format = dateTimeFormatter.format(zone);
        System.out.println(format);// 2022/07/25 21:16:48 周一 下午
    }
}
