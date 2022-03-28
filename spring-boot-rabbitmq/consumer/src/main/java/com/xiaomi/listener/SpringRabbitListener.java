package com.xiaomi.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "xiaomi.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenerDirectQueue1(String msg) throws InterruptedException {
        System.out.println("消费者接收到direct.queue1的消息....1：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "xiaomi.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenerDirectQueue2(String msg) throws InterruptedException {
        System.out.println("消费者接收到direct.queue2的消息....1：【" + msg + "】");
    }

}
