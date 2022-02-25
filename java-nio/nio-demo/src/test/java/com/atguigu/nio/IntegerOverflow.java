package com.atguigu.nio;

public class IntegerOverflow {
    public static void main(String[] args) {
        int l = 0;
        int r = Integer.MAX_VALUE - 1;
        // int m = (l + r) / 2;// l/2 + r/2 ==> l + (-l/2 + r/2) ==> l + (r-l) / 2
        // int m = l + (r - l) / 2; // 方法1
        int m = (l + r) >>> 1; // 方法2
        System.out.println(m);
        // 查找的值在最大值右侧
        l = m + 1;
        // m = (l + r) / 2;
        // m = l + (r - l) / 2;
        m = (l + r) >>> 1;
        System.out.println(m);
    }
}
