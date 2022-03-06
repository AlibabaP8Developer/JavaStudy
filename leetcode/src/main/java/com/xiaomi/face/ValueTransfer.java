package com.xiaomi.face;

public class ValueTransfer {
    /*
        值传递&引用传递 程序设计语言将实参传递给方法（或函数）的方式分为两种：
        值传递 ：方法接收的是实参值的拷贝，会创建副本。
        引用传递 ：方法接收的直接是实参所引用的对象在堆中的地址，不会创建副本，对形参的修改将影响到实参。
        很多程序设计语言（比如 C++、 Pascal )提供了两种参数传递的方式，不过，在 Java 中只有值传递。
     */
    public static void main(String[] args) {
        // 值传递
        int i = 10, j = 20;
        swap(i, j);
        System.out.println(i + "-" + j);

        System.out.println("=======引用传递=======");
        // 引用传递
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static void change(int[] array) {
        // 将数组的第一个元素变为10
        array[0] = 10;
    }

}
