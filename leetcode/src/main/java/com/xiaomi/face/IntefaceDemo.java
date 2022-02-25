package com.xiaomi.face;

public interface IntefaceDemo {
    void demo();

    static void sm() {
        System.out.println("interface提供的方式实现");
    }

    default void sm2() {
        System.out.println("interface提供的方式实现");
    }

}
