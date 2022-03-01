package com.xiaomi.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaomi
 */
public class CollectDemo01 {

    public static void main(String[] args) {

        Stream<String> stream = Stream.of("apple", "tencent", "alibaba");

        // 将流中数据收集到集合中
//        List<String> collect = stream.collect(Collectors.toList());

//        Set<String> collect = stream.collect(Collectors.toSet());

        // 收集到指定到集合中arraylist
//        ArrayList<String> collect = stream.collect(Collectors.toCollection(ArrayList::new));

//        HashSet<String> collect = stream.collect(Collectors.toCollection(HashSet::new));

        // 将流中数据收集到数组中 readonly
        // 转成object数组不方便
//        Object[] objects = stream.toArray();
//        for (Object object : objects) {
//            System.out.println(object);
//        }

        // String[]
        String[] strings = stream.toArray(String[]::new);
        for (String string : strings) {
            System.out.println(string);
            System.out.println(string.length());
        }

    }
}
