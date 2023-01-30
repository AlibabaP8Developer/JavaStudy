package com.itheima.interview;

public class StringDemo4 {

    public static void main(String[] args) {
        String phone = phone("15566663333");
        System.out.println(phone);
    }

    public static String phone(String str) {
        String start = str.substring(0, 3);
        String end = str.substring(7);
        String result = start +"****"+ end;
        return result;
    }
}
