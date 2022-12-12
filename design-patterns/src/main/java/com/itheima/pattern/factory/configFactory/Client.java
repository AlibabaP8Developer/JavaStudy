package com.itheima.pattern.factory.configFactory;

public class Client {

    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());

        Coffee latte = CoffeeFactory.createCoffee("latte");
        System.out.println(latte.getName());
    }
}
