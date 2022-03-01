package com.xiaomi.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author xiaomi
 */
public class GetStreamDemo03 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "朱元璋", "朱重八", "朱五四", "朱初一", "织田信长", "丰臣秀吉");
        long count = list.stream().count();
        System.out.println(count);

        System.out.println("================");

        // 得到流
        list.stream().forEach(System.out::println);

        System.out.println("========Stream流的filter方法========");
        // 调用流中的方法
//        list.stream().filter(s -> !s.equals("朱重八")).forEach(s -> {
//            System.out.println(s);
//        });

        // lambda可以转成方法引用
        list.stream().filter(s -> !"朱重八".equals(s)).forEach(System.out::println);
        // 得到长度超过3个字的名字（过滤）

        // Stream<T> filter(Predicate<? super T> predicate);
        list.stream().filter(s -> s.length() > 3).forEach(System.out::println);

        System.out.println("========Stream流的limit方法========");

        // limit 方法可以对流进行截取，只取用前n个。方法签名：
        // 获取前3个数据
        list.stream().limit(3).forEach(System.out::println);

        System.out.println("========Stream流的skip方法========");

        // 如果希望跳过前几个元素，可以使用 skip 方法获取一个截取之后的新流：
        // 跳过前3个数据
        list.stream().skip(3).forEach(System.out::println);

        System.out.println("========Stream流的map方法========");

        Stream<String> stream = Stream.of("45", "43", "23");
        // 将stream流中的字符串转成integer
        // Map可以将一种类型的流转换成另一种类型的流
        // stream.map(s -> Integer.parseInt(s)).forEach(System.out::println);
        stream.map(Integer::parseInt).forEach(System.out::println);

        System.out.println("========Stream流的sorted方法========");

        // Stream流的sorted方法
        // 如果需要将数据排序，可以使用 sorted 方法。方法签名：
        // Stream<T> sorted();
        // Stream<T> sorted(Comparator<? super T> comparator);
        Stream<Integer> stream1 = Stream.of(45, 43, 23);
        // 升序 默认
        // stream1.sorted().forEach(System.out::println);
        /*
            降序
         */
        /*stream1.sorted((x, y) -> {
            return y - x;
        }).forEach(System.out::println);*/
        // 简化
        stream1.sorted((x, y) -> y - x).forEach(System.out::println);

        System.out.println("========Stream流的distinct方法========");
        /*
            如果需要去除重复数据，可以使用 distinct 方法。方法签名：
            Stream<T> distinct();
         */
        Stream.of(33, 44, 55, 66, 33, 22, 44, 55).distinct().sorted((x, y) -> y - x).forEach(System.out::println);

        Stream.of("玄烨", "胤禛", "弘历", "弘历", "玄烨").distinct().forEach(System.out::println);
        System.out.println("========Stream流的distinct方法===[distinct对自定义对象去重]=====");
        // distinct对自定义对象去重
        Stream.of(
                new Person("玄烨", "男"),
                new Person("玄烨", "男"),
                new Person("朱重八", "男"),
                new Person("朱标", "男"),
                new Person("朱允文", "男"),
                new Person("朱允文", "男"),
                new Person("武则天", "女"),
                new Person("杨玉环", "女"),
                new Person("吕后", "女")).distinct().forEach(System.out::println);

        System.out.println("========Stream流的match方法========");
        /*
            如果需要判断数据是否匹配指定的条件，可以使用 Match 相关方法。方法签名：
            boolean allMatch(Predicate<? super T> predicate);
                匹配所有元素，所有元素都需要满足条件
            boolean anyMatch(Predicate<? super T> predicate);
                匹配某个元素，只有有其中一个元素满足条件即可
            boolean noneMatch(Predicate<? super T> predicate);
                匹配所有元素，所有元素都不满足条件
         */
        Stream<Integer> stream2 = Stream.of(33, 44, 55, 66, 30);
        /*boolean b = stream2.allMatch((a) -> {
            // 需要所有元素都满足a > 4
            return a > 4;
        });
        System.out.println(b);*/
        boolean b = stream2.noneMatch((a) -> a > 66);
        System.out.println(b);

        System.out.println("========Stream流的find方法========");

        /*
            如果需要找到某些数据，可以使用 find 相关方法。方法签名：
            Optional<T> findFirst();
            Optional<T> findAny();
         */
        Stream<Integer> stream3 = Stream.of(33, 44, 55, 66, 30);
//        Optional<Integer> first = stream3.findAny();
        Optional<Integer> first = stream3.findFirst();
        Integer integer = first.get();
        System.out.println(integer);

        System.out.println("========Stream流的max和min方法========");
        Stream<Integer> stream4 = Stream.of(33, 44, 55, 66, 30);
        Stream<Integer> stream5 = Stream.of(33, 44, 55, 66, 30);
        /*
            如果需要获取最大和最小值，可以使用 max 和 min 方法。方法签名：
            Optional<T> max(Comparator<? super T> comparator);
            Optional<T> min(Comparator<? super T> comparator);
            // 30, 33, 44, 55, 66
            max：是获取排序后最后一个值
            min：是获取排序后第一个值
         */
        Integer max = stream4.max((i, j) -> {
            return i - j;
        }).get();
        System.out.println("最大值：" + max);

        Integer min = stream5.min((i, j) -> i - j).get();
        System.out.println("最小值：" + min);

        System.out.println("========Stream流的reduce方法========");

        /*
            如果需要将所有数据归纳得到一个数据，可以使用 reduce 方法。方法签名：
            T identity：默认值
            BinaryOperator<T> accumulator：对数据进行处理对方式
            T reduce(T identity, BinaryOperator<T> accumulator);
         */
        int reduce = Stream.of(3,2,1).reduce(0, (i, j) -> {
            return i + j;
        });
        System.out.println(reduce);
    }
}
