package com.itheima.pattern.factory.before;

public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        // 声明coffee类型的变量，根据不同类型创建的coffee子类对象
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("您的咖啡不存在！");
        }
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
