package com.xiaomi.stream;

import java.util.*;
import java.util.stream.Stream;

public class GetStreamDemo01 {

    /**
     * 一个ArrayList集合中存储有以下数据:张无忌,周芷若,赵敏,张强,张三丰
     * 需求:1.拿到所有姓张的 2.拿到名字长度为3个字的 3.打印这些数据
     */
    public static void main(String[] args) {
        // 1.根据collection获取流
        // collection接口中有一个默认的方法：default Stream<E> stream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        Set<String>  set = new HashSet<>();
        Stream<String> stream1 = set.stream();

        Map<String, String> map = new HashMap<>();
        Stream<String> stream2 = map.keySet().stream();
        Stream<String> stream3 = map.values().stream();
        Stream<Map.Entry<String, String>> stream4 = map.entrySet().stream();

        // 2.stream中的静态方法of获取流
        // public static<T> Stream<T> of(T... values)
        Stream<String> stream5 = Stream.of("aa", "bb", "cc");
        String[] strs = {"aa", "cc", "bb"};
        Stream<String> stream6 = Stream.of(strs);

        // 基本数据类型的数组,不行，会将整个数组看作是一个元素进行操作，不会操作数组里面的元素
        int[] arr = {11,22,33};
        Stream<int[]> stream7 = Stream.of(arr);

    }
}
