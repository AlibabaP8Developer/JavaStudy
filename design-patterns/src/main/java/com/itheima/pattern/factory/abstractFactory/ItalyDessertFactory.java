package com.itheima.pattern.factory.abstractFactory;

/**
 * 意大利风味甜品工厂
 * 生产拿铁咖啡和提拉米苏
 */
public class ItalyDessertFactory implements DessertFactory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Trimisu();
    }
}
