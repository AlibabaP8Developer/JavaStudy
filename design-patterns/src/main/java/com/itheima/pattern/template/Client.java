package com.itheima.pattern.template;

public class Client {
    public static void main(String[] args) {
        ConcreteClass_BaoCai baoCai = new ConcreteClass_BaoCai();
        // 调用炒菜功能
        baoCai.cookProcess();
    }
}
