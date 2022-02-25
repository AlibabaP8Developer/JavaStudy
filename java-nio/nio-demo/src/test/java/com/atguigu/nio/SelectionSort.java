package com.atguigu.nio;

import java.util.Arrays;

import static com.atguigu.nio.BubbleSort.swap;

public class SelectionSort {

    public static void main(String[] args) {
        int[] a = {5, 9, 2, 4, 1, 6, 8, 7, 3};
        selection(a);
        System.out.println(a.length);
    }

    private static void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            // i 代表每轮选择最小元素要交换到的目标索引
            int s = i;// 代表最小元素的索引
            for (int j = s + 1; j < a.length; j++) {
                if (a[s] > a[j]) {
                    s = j;
                }
            }
            if (s != i) {
                swap(a, s, i);
            }
            System.out.println(Arrays.toString(a));
        }
    }
}
