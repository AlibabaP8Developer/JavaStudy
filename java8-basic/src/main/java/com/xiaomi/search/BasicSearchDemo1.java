package com.xiaomi.search;

/**
 * 基本查找/顺序查找
 */
public class BasicSearchDemo1 {

    public static void main(String[] args) {
        /*
            基本查找/顺序查找
            核心：
            从0索引开始挨个往后查找
            定义一个方法利用基本查找，查询某个元素是否存在
         */

        int[] arr = {3, 4, 5, 7};
        boolean b = basicSearch(arr, 3);
        System.out.println(b);
    }

    /**
     * @param arr    数组
     * @param number 要查找的元素
     * @return 元素是否存在
     */
    public static boolean basicSearch(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }
}
