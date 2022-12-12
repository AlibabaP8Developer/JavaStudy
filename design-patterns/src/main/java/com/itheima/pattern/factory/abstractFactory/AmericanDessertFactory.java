package com.itheima.pattern.factory.abstractFactory;

/**
 * 美式风味甜品工厂
 * 生产美式咖啡喝抹茶慕斯
 */
public class AmericanDessertFactory implements DessertFactory {

    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new MatchaMousse();
    }
}
