package com.itheima.pattern.builder.demo1;

/**
 * 具体的构建者，构建摩拜单车对象
 */
public class MobileBuilder extends Builder {

    @Override
    public void buildFrame() {
        bike.setFrame("碳纤维车架");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("真皮车座");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
