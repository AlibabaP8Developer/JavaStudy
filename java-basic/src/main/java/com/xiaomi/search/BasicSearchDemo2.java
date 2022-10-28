package com.xiaomi.search;

import java.util.ArrayList;
import java.util.List;

public class BasicSearchDemo2 {

    public static void main(String[] args) {
        /*
            基本查找/顺序查找
            核心：
            从0索引开始挨个往后查找
            1.定义一个方法利用基本查找，查询某个元素在数组中的索引
            要求：不需要考虑数组中元素是否重复
            2.定义一个方法利用基本查找，查询某个元素在数组中的索引
            要求：需要考虑数组中元素有重复的可能性
         */

        int[] arr = {13, 4, 15, 98, 30, 44, 15, 13};
        List<Integer> b = basicSearch(arr, 19);
        b.stream().forEach(System.out::println);
    }

    /**
     * @param arr    数组
     * @param number 要查找的元素
     * @return 元素是否存在
     */
    public static List<Integer> basicSearch(int[] arr, int number) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                list.add(i);
            }
        }
        return list;
    }
}
