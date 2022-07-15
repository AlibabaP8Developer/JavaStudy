package com.xiaomi.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 并行stream流
 */
public class ParallelDemo01 {
    public static void main(String[] args) {
//        Stream<Integer> stream = Stream.of(4, 5, 3, 6, 9, 7, 1, 8);
        // 串行流
//        stream.filter(s -> {
//            System.out.println(Thread.currentThread() + ":" + s);
//            return s > 3;
//        }).count();

        System.out.println("================");
        // 获取并行stream流的两种方式
        // 1.直接获取并行的stream流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.parallelStream();
        // 2.将串行流转成并行流
        Stream<String> parallel = list.stream().parallel();

        Stream.of(4, 5, 3, 6, 9, 7, 1, 8)
                .parallel() // 转并行流
                .filter(s->{
                    System.out.println(Thread.currentThread() + ":" + s);
                    return s > 3;
                }).count();
    }
}
