package com.xiaomi.localdate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class A01ZoneIdDemo3 {

    public static void main(String[] args) {
        // 当前本地年月日
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate birth = LocalDate.of(2001, 1, 1);
        System.out.println(birth);

        Period period = Period.between(birth, today);
        System.out.println("相差的时间间隔对象：" + period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.toTotalMonths());

        System.out.println("===============");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(1800, 1, 1, 0, 0, 0);
        Duration duration = Duration.between(of, now);
        System.out.println("相差的时间间隔对象：" + duration);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());

        System.out.println("===============");
        System.out.println("相差的年数："+ ChronoUnit.YEARS.between(of, now));
        System.out.println("相差的分钟数："+ ChronoUnit.MONTHS.between(of, now));
        System.out.println("相差的周数："+ ChronoUnit.WEEKS.between(of, now));
        System.out.println("相差的天数："+ ChronoUnit.DAYS.between(of, now));
        System.out.println("相差的小时数："+ ChronoUnit.HOURS.between(of, now));
        System.out.println("相差的分钟数："+ ChronoUnit.MINUTES.between(of, now));
        System.out.println("相差的秒数："+ ChronoUnit.SECONDS.between(of, now));
        System.out.println("相差的毫秒数："+ ChronoUnit.MILLIS.between(of, now));
        System.out.println("相差的微秒数："+ ChronoUnit.MICROS.between(of, now));
        System.out.println("相差的纳秒数："+ ChronoUnit.NANOS.between(of, now));
        System.out.println("相差的十年数："+ ChronoUnit.DECADES.between(of, now));
        System.out.println("相差的世纪数："+ ChronoUnit.CENTURIES.between(of, now));
        System.out.println("相差的千年数："+ ChronoUnit.MILLENNIA.between(of, now));
        System.out.println("相差的纪元数："+ ChronoUnit.ERAS.between(of, now));
    }
}
