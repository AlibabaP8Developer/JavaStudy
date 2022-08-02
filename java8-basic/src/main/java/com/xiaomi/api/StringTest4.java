package com.xiaomi.api;

import java.util.Scanner;
import java.util.StringJoiner;

public class StringTest4 {

    /**
     * 定义一个方法自己实现toBinaryString方法的效果，将一个十进制整数转成字符串表示的二进制
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = toBinaryString(16123);
        System.out.println(s);
    }

    public static String toBinaryString(int number) {
        // 核心逻辑：不断除以2，得到余数，一直到商为0结束
        // 需要把余数倒倒过来拼接
        // 定义一个StringJoiner用来拼接参数
        StringBuilder sb = new StringBuilder();
        // 利用循环除以2获取余数
        while (true) {
            if (number == 0) {
                break;
            }
            // 获取余数  %
            int remainder = number % 2;
            // 倒着拼接
            sb.insert(0, remainder);
            // 除以2  /
            number = number / 2;
        }
        return sb.toString();
    }
}
