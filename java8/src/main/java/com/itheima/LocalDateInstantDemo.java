package com.itheima;

import java.time.Instant;

public class LocalDateInstantDemo {
    public static void main(String[] args) {
        // Instant 内部保存了秒和纳秒，一般不是给用户使用，而是做一些统计的
        Instant now = Instant.now();
        System.out.println(now);

        Instant add = now.plusSeconds(20);
        System.out.println(add);
        Instant minus = now.minusSeconds(30);
        System.out.println(minus);
    }
}
