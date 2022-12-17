package com.itheima.pattern.decorator;

/**
 * 鸡蛋类（具体装饰者角色）
 */
public class Egg extends Garnish {

    public Egg(FastFood fastFood) {
        super(fastFood, 1.09f, "柴鸡蛋");
    }

    @Override
    public float cost() {
        // 计算价格
        System.out.println("鸡蛋价格"+ getPrice());
        System.out.println("炒饭价格"+ getFastFood().cost());
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
