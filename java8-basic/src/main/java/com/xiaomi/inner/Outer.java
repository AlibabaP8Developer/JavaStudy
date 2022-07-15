package com.xiaomi.inner;

public class Outer {

    int a = 10;
    static int b = 20;

    // 静态内部类
    static class Inner {
        public void show1() {
            Outer outer = new Outer();
            System.out.println("非静态的方法被调用了！"+ outer.a);
            System.out.println("非静态的方法被调用了！"+ b);
        }

        public static void show2() {
            Outer outer = new Outer();
            System.out.println("静态的方法被调用了！" + outer.a);
            System.out.println("非静态的方法被调用了！"+ b);
        }
    }
}
