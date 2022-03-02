package com.xiaomi.stream;

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
    }
}
