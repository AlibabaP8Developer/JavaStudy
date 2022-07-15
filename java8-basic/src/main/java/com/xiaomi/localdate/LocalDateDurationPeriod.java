package com.xiaomi.localdate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

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
        Duration between = Duration.between(time, now);
        System.out.println("相差的天数："+between.toDays());
        System.out.println("相差的小时："+between.toHours());
        System.out.println("相差的分钟："+between.toMinutes());
        System.out.println("相差的纳秒："+between.toNanos());
        System.out.println("相差的秒：" +between.getSeconds());

        System.out.println("==================");
        // Period 计算日期的距离
        LocalDate localDate = LocalDate.now();
        LocalDate date = LocalDate.of(2000, 8, 9);
        // 后面的时间减去前面的时间
        Period between1 = Period.between(date, localDate);
        System.out.println("相差的年数："+between1.getYears());
        System.out.println("相差的天数："+between1.getDays());
        System.out.println("相差的月数："+between1.getMonths());
    }
}
