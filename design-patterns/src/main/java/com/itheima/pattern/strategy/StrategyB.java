package com.itheima.pattern.strategy;

/**
 * 具体策略类，用来封装算法
 */
public class StrategyB implements Strategy {
    @Override
    public void show() {
        System.out.println("满200元减50元");
    }
}
