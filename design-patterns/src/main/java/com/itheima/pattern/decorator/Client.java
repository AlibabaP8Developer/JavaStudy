package com.itheima.pattern.decorator;

public class Client {
    public static void main(String[] args) {
        // 点炒饭
        FastFood rice = new FriedRice();
        System.out.println(rice.getDesc() +" : "+ rice.cost() + "元");

        System.out.println("========");

        // 在炒饭加一个鸡蛋
        rice = new Bacon(rice);
        System.out.println(rice.getDesc() +" : "+ rice.cost() + "元");
    }
}
