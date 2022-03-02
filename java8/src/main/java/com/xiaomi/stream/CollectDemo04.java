package com.xiaomi.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo04 {
    public static void main(String[] args) {
        // 对流中数据进行多级分组
        Stream<Person> stream = Stream.of(
                new Person("刘如意", "男", 10),
                new Person("吕雉", "女", 20),
                new Person("刘盈", "男", 30),
                new Person("刘肥", "男", 53),
                new Person("刘彻", "男", 23),
                new Person("武则天", "女", 40),
                new Person("马皇后", "女", 14),
                new Person("杨贵妃", "女", 31),
                new Person("慈禧老佛爷", "女", 28));

        // 先根据年龄分组，每组中在根据成绩分组
        /*
            Collector<T, ?, Map<K, D>> groupingBy(
                Function<? super T, ? extends K> classifier,
                Collector<? super T, A, D> downstream
            )
         */
        Map<Integer, Map<String, List<Person>>> map = stream.collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(p -> {
            if (p.getAge() > 20) {
                return "成年人";
            } else {
                return "未成年人";
            }
        })));

        map.forEach((k, v) -> {
            System.out.println("k:" + k);
            v.forEach((k2, v2) -> {
                System.out.println("\t" + k2 + ":" + v2);
            });
        });
    }
}
