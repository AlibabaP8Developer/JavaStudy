package com.itheima.pattern.command;

import java.util.Map;

/**
 * 具体的命令类
 */
public class OrderCommand implements Command {

    // 持有接收者对象
    private SeniorChef receiver;
    private Order order;

    public OrderCommand(SeniorChef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getDiningTable() + "号桌订单：");
        Map<String, Integer> foodDir = order.getFoodDir();
        // 遍历map
        for (String foodName : foodDir.keySet()) {
            receiver.makeFood(foodName, foodDir.get(foodName));
        }
        System.out.println(order.getDiningTable()+ "号桌的饭准备完毕！");
    }
}
