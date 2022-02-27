package com.xiaomi.localdate;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * JDK8时间校正器
 * 如：将日期调整到下一个月的第一天等操作
 * TemporalAdjuster：时间校正器
 * TemporalAdjusters：该类通过静态方法提供类大量的常用TemporalAdjuster的实现
 */
public class TemporalDemo {
    public static void main(String[] args) {
        // 将日期调整到下一个月的第一天
        LocalDateTime now = LocalDateTime.now();
        TemporalAdjuster firstDayOfNextMonth = (temporal) -> {
            // temporal要调整的时间
            LocalDateTime dateTime = (LocalDateTime) temporal;
            LocalDateTime localDateTime = dateTime.plusMonths(1).withDayOfMonth(1);
            return localDateTime;
        };
        LocalDateTime nextDateTime = now.with(firstDayOfNextMonth);
        System.out.println(nextDateTime);
    }
}
