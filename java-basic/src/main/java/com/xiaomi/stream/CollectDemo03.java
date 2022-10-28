package com.xiaomi.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对流中数据进行分组、多级分组
 */
public class CollectDemo03 {
    public static void main(String[] args) {
        // 对流中数据进行分组
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

        // 第一种方式
        // Function<? super T, ? extends K> classifier
//        Map<String, List<Person>> map = stream.collect(Collectors.groupingBy(Person::getSex));
        // 第二种方式
//        Map<String, List<Person>> map = stream.collect(Collectors.groupingBy(p -> {
//            return p.getSex();
//        }));

        // 将年龄大于30的分为一组，小于30的分为一组
        Map<String, List<Person>> map = stream.collect(Collectors.groupingBy(p -> {
            if (p.getAge() > 30) {
                return "成年人";
            } else {
                return "未成年人";
            }
        }));

        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}
