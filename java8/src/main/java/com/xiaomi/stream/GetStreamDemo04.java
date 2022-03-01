package com.xiaomi.stream;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Stream流的reduce和map方法混合使用
 * @author xiaomi
 */
public class GetStreamDemo04 {
    public static void main(String[] args) {
        /*
            求出所有年龄的总和
            1 得到所有的年龄
            2 让年龄相加
         */
        Integer totalAge = Stream.of(
                new Person("朱元璋", 20),
                new Person("朱重八", 10),
                new Person("朱由检", 30),
                new Person("朱高炽", 40))
                .map(p -> p.getAge())
                .reduce(0, Integer::sum);
//                .reduce(0, (i, j) -> Integer.sum(i, j));
        System.out.println("所有年龄的总和：" + totalAge);

        /*
            找出最大年龄
            1.获取所有的年龄
            2.获取最大的年龄
         */
        Integer maxAge = Stream.of(new Person("朱元璋", 20),
                new Person("朱重八", 10),
                new Person("朱由检", 30),
                new Person("朱高炽", 40))
                .map(p -> p.getAge())
                .reduce(0, Math::max);
//                .reduce(0, (i, j) -> i > j ? i : j);
        System.out.println("最大的年龄：" + maxAge);

        /*
            统计a出现的次数
         */
        Integer count = Objects.requireNonNull(Stream.of("a", "b", "c", "a", "d", "e", "a")
                .map(s -> {
                    if ("a".equals(s)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })).reduce(0, Integer::sum);
        System.out.println("统计a出现的次数：" + count);
    }
}
