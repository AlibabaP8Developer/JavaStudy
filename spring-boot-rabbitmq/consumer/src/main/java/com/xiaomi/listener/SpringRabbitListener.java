package com.xiaomi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueueMessage(String msg) {
        System.out.println("spring消费者接收到的消息：【" + msg + "】");
    }
}
