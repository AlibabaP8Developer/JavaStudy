package com.atguigu.nio;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = {5, 9, 2, 4, 1, 6, 8, 7, 3};
        insert(a);
        System.out.println(Arrays.toString(a));
    }

    private static void insert(int[] a) {
        // i代表待插入元素的索引
        for (int i = 1; i < a.length; i++) {
            int t = a[i];// 代表待插入元素的值
            int j = i - 1; // 代表已排序区的元素索引
            while (j >= 0) {
                if (t < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
                j--;
            }
            a[j + 1] = t;
            System.out.println(Arrays.toString(a));
        }
    }
}
