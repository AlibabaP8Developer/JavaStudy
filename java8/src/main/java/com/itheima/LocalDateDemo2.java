package com.itheima;

import java.time.LocalDateTime;

public class LocalDateDemo2 {
    /**
     * 比较时间
     * @param args
     */
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 12, 00, 00);
        LocalDateTime now = LocalDateTime.now();
        //now在dateTime后面?
        boolean after = now.isAfter(dateTime);
        //now在dateTime前面?
        boolean before = now.isBefore(dateTime);
        boolean equal = now.isEqual(dateTime);
        System.out.println(after);
        System.out.println(before);
        System.out.println(equal);
    }
}
