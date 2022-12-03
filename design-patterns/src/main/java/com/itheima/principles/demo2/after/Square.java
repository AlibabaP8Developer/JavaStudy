package com.itheima.principles.demo2.after;

/**
 * 正方形类
 */
public class Square implements Quadrilateral {

    // 边长
    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
