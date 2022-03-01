package com.xiaomi.stream;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对流中数据进行聚合计算
 * 当我们使用Stream流处理数据后，可以像数据库的聚合函数一样对某个字段进行操作。
 * 比如获取最大值，获取最小值，求总和，平均值，统计数量。
 * @author alibaba
 */
public class CollectDemo02 {
    public static void main(String[] args) {
        // 其他收集流中数据的方式（相当于数据库中的聚合函数）
        Stream<Person> stream = Stream.of(
                new Person("刘如意", "男", 10),
                new Person("吕雉", "女", 20),
                new Person("刘盈", "男", 30),
                new Person("刘肥", "男", 53),
                new Person("刘彻", "男", 60),
                new Person("武则天", "女", 40));

        // 获取最大值
//        Optional<Person> max = stream.collect(Collectors.maxBy((i, j) -> {
//            return i.getAge() - j.getAge();
//        }));
//        System.out.println(collect1.get());

        // 获取最小值
//        Optional<Person> min = stream.collect(Collectors.minBy((i, j) -> {
//            return i.getAge() - j.getAge();
//        }));
//        System.out.println(collect2.get());

        // 总和
//        Integer sum = stream.collect(Collectors.summingInt(p -> p.getAge()));

        // 平均值
//        Double avg = stream.collect(Collectors.averagingInt(p -> p.getAge()));
//        Double avg = stream.collect(Collectors.averagingInt(Person::getAge));

        // 统计数量
        Long count = stream.collect(Collectors.counting());
        System.out.println(count);
    }
}
