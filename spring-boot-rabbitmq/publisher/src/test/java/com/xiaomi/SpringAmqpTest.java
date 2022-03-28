package com.xiaomi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!!!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendFanoutExchange() {
        // 交换机名称
        String exchangeName = "xiaomi.fanout";
        String message = "hello, every one!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "xiaomi.direct";
        String message = "hello, every one!";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }
}
