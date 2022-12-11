package com.itheima.pattern.factory.staticFactory;

/**
 * 咖啡类
 */
public abstract class Coffee {

    public abstract String getName();

    public void addSugar() {
        System.out.println("加糖");
    }

    public void addMilk() {
        System.out.println("加奶");
    }
}
