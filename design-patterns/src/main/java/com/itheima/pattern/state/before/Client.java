package com.itheima.pattern.state.before;

public class Client {
    public static void main(String[] args) {
        // 创建电梯对象
        Lift lift = new Lift();
        //设置电梯当前状态
        lift.setState(ILift.RUNNING_STATE);
        // 开门
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }
}
