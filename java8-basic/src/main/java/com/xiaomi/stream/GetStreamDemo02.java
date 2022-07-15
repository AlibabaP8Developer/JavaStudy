package com.xiaomi.stream;

import java.util.stream.Stream;

/**
 * Stream常用方法
 */
public class GetStreamDemo02 {
    public static void main(String[] args) {
        Stream<String> stream5 = Stream.of("aa", "bb", "cc");
        // 1. Stream只能操作一次
//        long count = stream5.count();
//        long count2 = stream5.count();
//        System.out.println(count);

        // 2. Stream方法返回的是新的流
//        Stream<String> limit = stream5.limit(1);
//        System.out.println(limit);
//        System.out.println(stream5);

        // 3. Stream不调用终结方法，中间的操作不会执行
        stream5.filter(s -> {
            System.out.println(s);
            return true;
        }).count();

    }
}
