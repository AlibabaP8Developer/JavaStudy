package com.itheima.interview;

public class StringDemo5 {
    public static void main(String[] args) {
        // 定义一个字符串记录身份证号码
        String id = "150430199909110018";
        String year = id.substring(6, 10);
        String month = id.substring(10, 12);
        String day = id.substring(12, 14);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        char gender = id.charAt(16);
        System.out.println(gender);
    }
}
