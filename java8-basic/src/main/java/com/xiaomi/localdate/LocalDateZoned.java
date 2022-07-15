package com.xiaomi.localdate;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * JDK8设置日期时间的时区
 * Java8 中加入了对时区的支持，LocalDate、LocalTime、LocalDateTime是不带时区的，
 * 带时区的日期时间类分别为：ZonedDate、ZonedTime、ZonedDateTime。
 * 其中每个时区都对应着 ID，ID的格式为 “区域/城市” 。例如 ：Asia/Shanghai 等。
 * ZoneId：该类中包含了所有的时区信息。
 */
public class LocalDateZoned {

    public static void main(String[] args) {
        // 1.获取所有的时区ID
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);

        // 不需时区，获取计算机的当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now：" + now);

        // 2.操作带时区带类
        // 世界标准时间
        // 东八区比标准时间早8小时
        // zonedDateTime：2022-02-27T03:23:26.955Z
        ZonedDateTime zonedDateTime = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("zonedDateTime：" + zonedDateTime);
        // now()：使用计算机的默认的时区
        // zonedDateTime1：2022-02-27T11:23:26.955+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        // zonedDateTime1：2022-02-27T11:27:41.497+08:00[Asia/Shanghai]
        System.out.println("zonedDateTime1：" + zonedDateTime1);

        // 使用指定的时区创建日期和时间
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("America/Toronto"));
        // America/Toronto：2022-02-26T22:27:41.499-05:00[America/Toronto]
        System.out.println("America/Toronto：" + now1);

        // 修改时区
        // withZoneSameInstant：更改时区，也更改时间
        ZonedDateTime japan = now1.withZoneSameInstant(ZoneId.of("Japan"));
        System.out.println("japan："+japan);

        // withZoneSameLocal：更改时区
        ZonedDateTime japan1 = now1.withZoneSameLocal(ZoneId.of("Japan"));
        System.out.println("japan1："+japan1);
    }
}
