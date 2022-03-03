package com.xiaomi.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo06 {
    public static void main(String[] args) {
        /*
            对流中数据进行拼接
            Collectors.joining 会根据指定的连接符，将所有元素连接成一个字符串
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

        // 根据一个字符串拼接
//        String names = stream.map(Person::getName).collect(Collectors.joining("_"));
//        System.out.println(names);

        // 根据三个字符串拼接，前缀和后缀
        String names = stream.map(person -> person.getName()).collect(Collectors.joining("_", "@", "$"));
        System.out.println(names);
    }
}
