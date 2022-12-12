package com.itheima.pattern.builder.demo1;

public class Client {
    public static void main(String[] args) {
        // 创建指挥者对象
        Director director = new Director(new MobileBuilder());
        // 让指挥者指挥组装自行车
        Bike bike = director.construct();
        System.out.println(bike.getFrame());
        System.out.println(bike.getSeat());
    }
}
