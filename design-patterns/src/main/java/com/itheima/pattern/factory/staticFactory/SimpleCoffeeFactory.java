package com.itheima.pattern.factory.staticFactory;

/**
 * 简单咖啡工厂类，用来生产咖啡
 */
public class SimpleCoffeeFactory {
    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("您的咖啡不存在！");
        }
        return coffee;
    }
}
