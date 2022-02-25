package com.atguigu.nio;

import java.util.Arrays;

import static com.atguigu.nio.BubbleSort.swap;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {5, 9, 2, 4, 1, 6, 8, 7, 3};
        partition(a, 0, a.length - 1);
    }

    /**
     *
     * @param a
     * @param l
     * @param h 最右侧  上边界  基准点元素索引
     * @return
     */
    private static int partition(int[] a, int l, int h) {
        // 定义基准点
        int pv = a[h];
        int i = l;
        for (int j = l; j < h; j++) {
            if (a[j] < pv) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, h, i);
        System.out.println(Arrays.toString(a) + ", i=" + i);
        // 返回值代表了基准点元素所在的正确索引，用它确定下一轮分区的边界
        return 0;
    }
}
