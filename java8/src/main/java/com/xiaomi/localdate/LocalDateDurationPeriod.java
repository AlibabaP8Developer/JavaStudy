package com.xiaomi.localdate;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 计算日期时间差类
 * 1.Duration：用于计算2个时间（LocalTime，时分秒的距离）
 * 2.Period：用于计算2个日期（LocalDate，年月日的距离）
 * @author xiaomi
 */
public class LocalDateDurationPeriod {
    public static void main(String[] args) {
        // Duration 计算时间的距离
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(10, 00, 00);
        Duration between = Duration.between(now, time);
        System.out.println("相差的天数："+between.toDays());
        System.out.println("相差的小时："+between.toHours());
        System.out.println("相差的分钟："+between.toMinutes());
        System.out.println("相差的纳秒："+between.toNanos());
        System.out.println("相差的秒：" +between.getSeconds());
    }
}
