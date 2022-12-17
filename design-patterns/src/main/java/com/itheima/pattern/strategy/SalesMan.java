package com.itheima.pattern.strategy;

/**
 * 促销员，环境类，聚合策略类
 */
public class SalesMan {

    // 聚合策略类对象
    private Strategy strategy;

    public SalesMan(Strategy strategy) {
        this.strategy = strategy;
    }

    // 由促销员展示促销活动给用户
    public void salesManShow() {
        strategy.show();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
