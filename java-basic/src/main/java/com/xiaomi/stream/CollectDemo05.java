package com.xiaomi.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo05 {
    public static void main(String[] args) {
        /*
            对流中数据进行分区
            Collectors.partitioningBy 会根据值是否为true，
            把集合分割为两个列表，一个true列表，一个false列表。
         */
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

        Map<Boolean, List<Person>> map = stream.collect(Collectors.partitioningBy(p -> "男".equals(p.getSex())));

        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}
