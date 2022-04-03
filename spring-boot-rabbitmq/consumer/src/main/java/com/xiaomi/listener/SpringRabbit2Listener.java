package com.xiaomi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbit2Listener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg) {
        System.out.println("消费者收到simple.queue的消息：" + msg);
    }

}
