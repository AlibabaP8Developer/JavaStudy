package com.itheima.interview;

/**
 * 字符串拼接
 */
public class StringDemo1 {
    public static void main(String[] args) {
        int[] array = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        String s = arrToString(array);
        System.out.println(s);
    }

    public static String arrToString(int[] arr) {
        if (arr == null) {
            return "";
        }

        if (arr.length == 0) {
            return "[]";
        }

        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1) {
                result += arr[i];
            } else {
                result += arr[i] + ", ";
            }
        }
        result += "]";
        return result;
    }
}
