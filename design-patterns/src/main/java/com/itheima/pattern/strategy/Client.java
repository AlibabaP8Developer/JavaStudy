package com.itheima.pattern.strategy;

public class Client {
    public static void main(String[] args) {
        // 农历元旦，促销活动
        SalesMan salesMan = new SalesMan(new StrategyA());
        salesMan.salesManShow();

        System.out.println("=======");

        // 中秋节
        salesMan.setStrategy(new StrategyB());
        salesMan.salesManShow();

        // 端午节
        salesMan.setStrategy(new StrategyC());
        salesMan.salesManShow();
    }
}
