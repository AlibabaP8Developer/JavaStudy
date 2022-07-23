package com.xiaomi.api;

import java.util.Scanner;
import java.util.StringJoiner;

public class LuomaTest {

    /**
     * 一个字符串
     * 要求1：长度为小于等于9
     * 要求2：只能是数字
     * 将内容变成罗马数字
     * 下面是阿拉伯数字跟罗马数字对比关系：
     * I-1，II-2，III-3，IV-4，V-5，VI-6，VII-7，VIII-8，IX-9
     * 罗马数字里面没有0
     * 如果输入0，可以变成""
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "";
        Scanner scanner = new Scanner(System.in);
        // 校验字符串规则
        while (true) {
            System.out.println("请输入一个字符串：");
            str = scanner.next();
            boolean flag = checkStr(str);
            if (flag) {
                break;
            } else {
                System.out.println("当前的字符串不符合规则，请重新输入！");
                continue;
            }
        }

        StringJoiner sj = new StringJoiner(",");
        // 查表法：数字跟数据产生一个对应关系
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 字符1转换数字1
            int number = c - 48;
            String s = changeLuoMa(number);
            sj.add(s);
        }

        System.out.println(sj);
    }

    private static String changeLuoMa(int number) {
        String[] arr = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        return arr[number];
    }

    private static boolean checkStr(String str) {
        // 长度为小于等于9
        if (str.length() > 9) {
            return false;
        }
        // 只能是数字
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
