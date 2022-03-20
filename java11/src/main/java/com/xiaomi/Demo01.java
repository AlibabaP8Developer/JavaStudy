package com.xiaomi;

/**
 * JEP 181：基于嵌套的访问控制
 */
public class Demo01 {

}

class Outter {
    private int outInt;

    // 成员内部类
    public class Inner {
        public void test() {
            System.out.println("outInt:" + outInt);
        }
    }
}
