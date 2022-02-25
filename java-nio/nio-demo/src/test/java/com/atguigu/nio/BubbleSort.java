package com.atguigu.nio;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        // int[] a = {5, 2, 7, 4, 1, 3, 8, 9};
        int[] a = {5, 9, 2, 4, 1, 6, 8, 7, 3};
        bubbleV2(a);
    }

    /**
     * 改进冒泡
     *
     * @param a
     */
    public static void bubbleV2(int[] a) {
        // 循环次数
        int n = a.length - 1;
        while (true) {
            // 最后一次交换时的索引位置
            int last = 0;
            for (int j = 0; j < n; j++) {
                System.out.println("比较次数:" + j);
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    // 记录最后一次交换时的索引位置
                    last = j;
                }
            }
            n = last;
            System.out.println("第轮冒泡:" + Arrays.toString(a));
            if (n == 0) {
                break;
            }
        }
    }

    public static void bubble(int[] a) {
        for (int j = 0; j < a.length - 1; j++) {
            // 是否发生了交换
            boolean swapped = false;
            // 一轮冒泡
            for (int i = 0; i < a.length - 1 - j; i++) {
                System.out.println("比较次数:" + i);
                // 判断如果前面的数大于后一个数
                if (a[i] > a[i + 1]) {
                    // 交换位置
                    swap(a, i, i + 1);
                    swapped = true;
                }
            }
            System.out.println("第" + j + "轮冒泡:" + Arrays.toString(a));
            if (!swapped) {
                break;
            }
        }
        System.out.println("最终：" + Arrays.toString(a));
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
