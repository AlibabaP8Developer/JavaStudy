package com.itheima.pattern.factory.factoryMethod;

public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        CoffeeFactory coffeeFactory = new AmericanCoffeeFactory();
        store.setFactory(coffeeFactory);
        Coffee coffee = store.orderCoffee();
        System.out.println(coffee.getName());
    }
}
