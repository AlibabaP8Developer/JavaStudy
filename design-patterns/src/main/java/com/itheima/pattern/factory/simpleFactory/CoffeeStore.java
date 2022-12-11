package com.itheima.pattern.factory.simpleFactory;

public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        // 调用生产咖啡的方法
        Coffee coffee = factory.createCoffee(type);
        coffee.addMilk();
        coffee.addMilk();
        return coffee;
    }
}
