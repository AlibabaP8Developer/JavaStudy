package com.itheima.interview;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int target = 48;
        int idx = binarySearch(array, target);
        System.out.println(idx);
    }

    /**
     * 二分查找，找到返回元素索引，找不到返回-1
     *
     * @param a
     * @param t
     * @return
     */
    public static int binarySearch(int[] a, int t) {
        // 定义左右边界和中间索引
        int left = 0, right = a.length - 1, m;
        while (left <= right) {
            m = (left + right) / 2;
            if (a[m] == t) {
                return m;
            } else if (a[m] > t) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }
        return -1;
    }
}
