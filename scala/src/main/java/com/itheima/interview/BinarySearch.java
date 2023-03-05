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
     * @param a      待查找的升序数组
     * @param target 待查找的目标值
     * @return 找到则返回索引，找不到返回-1
     */
    public static int binarySearch(int[] a, int target) {
        // 定义左右边界和中间索引
        int left = 0, right = a.length - 1, m;
        // 范围内有东西
        while (left <= right) {
            m = (left + right) / 2;
            // 找到了
            if (a[m] == target) {
                return m;
            } else if (a[m] > target) { // 目标在左边
                right = m - 1;
            } else { // 目标在右边
                left = m + 1;
            }
        }
        return -1;
    }
}
