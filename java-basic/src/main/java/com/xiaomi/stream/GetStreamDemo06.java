package com.xiaomi.stream;

import java.util.stream.Stream;

/**
 * Stream流的concat方法
 * 如果有两个流，希望合并成为一个流，那么可以使用 Stream 接口的静态方法 concat：
 * static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
 * 备注：这是一个静态方法，与 java.lang.String 当中的 concat 方法是不同的。
 * @author xiaomi
 */
public class GetStreamDemo06 {
    public static void main(String[] args) {
        Stream<String> streamA = Stream.of("张三");
        Stream<String> streamB = Stream.of("李四");
        // 合并成一个流
        Stream<String> concat = Stream.concat(streamA, streamB);
        // 注意：合并成一个流后，不能操作之前的流了
        concat.forEach(System.out::println);
    }
}
