package com.xiaomi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    //@RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueueMessage(String msg) {
        System.out.println("spring消费者接收到的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue1(String msg) throws InterruptedException {
        System.out.println("spring消费者接收到的消息----1：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2......接收到的消息----2：【" + msg + "】");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者接收到的anout.queue1消息....1：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String msg) throws InterruptedException {
        System.out.println("消费者接收到fanout.queue2的消息....2：【" + msg + "】");
    }
}
