package com.itheima.interview;

/**
 * 字符串反转
 */
public class StringDemo2 {
    public static void main(String[] args) {
        String abcde = reverser("abcde");
        System.out.println(abcde);
    }

    public static String reverser(String str) {
        String result = "";
        // forr倒着遍历
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            result += c;
        }
        return result;
    }
}
