package com.xiaomi.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbit2Listener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg) {
        log.debug("消费者收到simple.queue的消息：[" + msg + "]");
        // 返回nack，失败了mq重新投递
        System.out.println(1/0);
        log.info("消费者处理消息成功！");
    }

}
