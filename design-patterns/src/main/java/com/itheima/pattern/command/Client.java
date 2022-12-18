package com.itheima.pattern.command;

public class Client {
    public static void main(String[] args) {
        // 创建第一个订单对象
        Order order = new Order();
        order.setDiningTable(1);
        order.setFood("锅包肉", 1);
        order.setFood("大窑", 2);

        Order order2 = new Order();
        order2.setDiningTable(1);
        order2.setFood("京酱肉丝", 1);
        order2.setFood("北冰洋", 2);

        // 创建厨师对象
        SeniorChef receiver = new SeniorChef();
        // 创建命令对象
        OrderCommand orderCommand1 = new OrderCommand(receiver, order);
        OrderCommand orderCommand2 = new OrderCommand(receiver, order2);
        // 创建调用者（服务员）对象
        Waitor invoke = new Waitor();
        invoke.setCommand(orderCommand1);
        invoke.setCommand(orderCommand2);

        // 让服务员发起命令
        invoke.orderUp();
    }
}
