package com.itheima.pattern.factory.factoryMethod;

/**
 * 拿铁咖啡工厂对象
 */
public class LatteCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
