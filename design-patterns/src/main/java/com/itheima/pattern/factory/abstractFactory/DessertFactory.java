package com.itheima.pattern.factory.abstractFactory;

/**
 * 甜品工厂接口
 */
public interface DessertFactory {

    // 生产咖啡的功能
    Coffee createCoffee();

    // 生产甜品功能
    Dessert createDessert();

}
