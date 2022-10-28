package com.xiaomi.api;

import java.util.StringJoiner;

public class StringTest2 {

    /**
     * 定义一个方法，实现字符串反转
     * 例如：
     * 数组为 int[] arr = [1,2,3]
     * 执行方法后的输出结果为：[1,2,3]
     *
     * @param args
     */
    public static void main(String[] args) {
        String reverse = reverse("apple pen");
        System.out.println(reverse);
    }

    public static String reverse(String str) {
        String result = "";
        // forr
        for (int i = str.length() - 1; i >= 0; i--) {
            // 依次表示字符串中的每一个索引 倒着的
            char c = str.charAt(i);
            result = result + c;
        }
        return result;
    }
}
