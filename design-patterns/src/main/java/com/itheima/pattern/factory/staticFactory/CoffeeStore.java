package com.itheima.pattern.factory.staticFactory;

public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);
        coffee.addMilk();
        coffee.addMilk();
        return coffee;
    }
}
