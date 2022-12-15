package com.itheima.proxy.staticProxy;

/**
 * 代收点卖票
 */
public class ProxyPoint implements SellTickets {

    // 声明火车站类对象
    private TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        // 代理类对象可以对方法体、返回值、参数等进行增强
        System.out.println("代理点收取一些服务费用");
        trainStation.sell();
    }
}
