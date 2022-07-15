package com.xiaomi.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream流的mapToInt
 *
 * @author xiaomi
 */
public class GetStreamDemo05 {

    public static void main(String[] args) {
        /*
            如果需要将Stream中的Integer类型数据转成int类型，可以使用 mapToInt 方法。方法签名：
            IntStream mapToInt(ToIntFunction<? super T> mapper);
            Integer占用的内存比int多，在stream流操作中会自动装箱和拆箱
         */
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        // 把大于3的打印出来
        stream.filter(i -> i > 3).forEach(System.out::println);

        System.out.println("=================");
        // IntStream内部操作的是int类型的数据，就可以节省内存，减少自动装箱和拆箱
        /*IntStream intStream = Stream.of(1, 2, 3, 4, 5).mapToInt((Integer i) -> {
            return i.intValue();
        });*/
        IntStream intStream = Stream.of(1, 2, 3, 4, 5).mapToInt(Integer::intValue);
        intStream.filter(i -> i > 3).forEach(System.out::println);
    }
}
