package com.xiaomi.api;

import java.util.StringJoiner;

public class StringTest1 {

    /**
     * 定义一个方法，把int数组中的数据按照指定的格式拼接成一个字符串返回，调用该方法，并在控制台输出结果
     * 例如：
     * 数组为 int[] arr = [1,2,3]
     * 执行方法后的输出结果为：[1,2,3]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 4};
        String s = arrToString(arr);
        System.out.println(s);// [134
    }

    public static String arrToString(int[] arr) {
        if (arr == null) {
            return "";
        }

        if (arr.length == 0) {
            return "[]";
        }

        //String result = "[";
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < arr.length; i++) {
            //if (i == arr.length-1) {
                //result = result + arr[i];
            //} else {
                //result = result + arr[i] + ", ";
            //}
            sj.add(arr[i] + "");
        }
        //result = result + "]";
        return sj.toString();
    }
}
