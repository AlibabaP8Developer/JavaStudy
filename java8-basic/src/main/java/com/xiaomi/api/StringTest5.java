package com.xiaomi.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class StringTest5 {

    /**
     * 实现计算你活了多少天，用jdk7和8完成
     *
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        jdk7();
        jdk8();
    }

    private static void jdk7() throws ParseException {
        // jdk7
        // 规则：只要对时间进行计算或判断，都需要先获取当前时间的毫秒值
        // 1 计算出生年月日的毫秒值
        String birthday = "2020年1月1日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = sdf.parse(birthday);
        long birthdayTime = date.getTime();

        // 2 获取当前时间的毫秒值
        long todayTime = System.currentTimeMillis();

        // 3 计算间隔多少天
        long time = todayTime - birthdayTime;
        System.out.println(time / 1000 / 60 / 60 / 24);
    }

    public static void jdk8() {
        LocalDate ldt = LocalDate.of(2022, 7,1);
        LocalDate now = LocalDate.now();
        // 第二个参数-第一个参数
        long between = ChronoUnit.DAYS.between(ldt, now);
        System.out.println(between);
    }
}
