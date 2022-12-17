package com.itheima.pattern.decorator;

/**
 * 快餐类
 */
public abstract class FastFood {

    /**
     * 价格
     */
    private float price;
    /**
     * 描述
     */
    private String desc;

    public FastFood() {
    }

    public FastFood(float price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 计算价格
     */
    public abstract float cost();
}
