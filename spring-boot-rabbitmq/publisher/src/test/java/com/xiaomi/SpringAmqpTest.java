package com.xiaomi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "xiaomi.topic";
        String message = "hello, every one, xiaomi.topic!";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

    @Test
    public void testSendObjectQueue() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "大明太祖 洪武 高皇帝 朱元璋");
        msg.put("age", 20);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
