package com.xiaomi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testLazyQueue() {
        for (int i = 0; i < 100000; i++) {
            Message message = MessageBuilder.withBody("hello, spring lazy!".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
            // 发送消息
            rabbitTemplate.convertAndSend("lazy.queue", message);
        }
    }

    @Test
    public void testNormalQueue() {
        for (int i = 0; i < 100000; i++) {
            Message message = MessageBuilder.withBody("hello, spring lazy!".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
            // 发送消息
            rabbitTemplate.convertAndSend("normal.queue", message);
        }
    }

    /**
     * 持久化
     */
    @Test
    public void testDurableMessage() {
        Message message = MessageBuilder.withBody("hello, spring".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        // 发送消息
        rabbitTemplate.convertAndSend("simple.queue", message);
    }

    @Test
    public void testSendMessageSimpleQueue() {
        String routingKey = "simpl.test";
        // 准备消息
        String msg = "hello, spring amqp!";
        // 准备correlationdata
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(result -> {
            // 成功回调
            // 判断结果
            if (result.isAck()) {
                // ack
                log.debug("消息投递到交换机成功！消息ID：{}", correlationData.getId());
            } else {
                // nack
                log.error("消息投递到交换机失败！消息ID：{}", correlationData.getId());
                // 重复消息
            }
        }, ex -> {
            // 失败回调
            log.error("消息发送失败！", ex);
            // 重复消息
        });
        // 发送消息
        rabbitTemplate.convertAndSend("amq.topic", routingKey, msg, correlationData);
    }

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
        String message = "hello, every one, only!!!!!!!!";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback((SuccessCallback<CorrelationData.Confirm>) result -> {
            if (result.isAck()) {
                // ack
                log.debug("消息成功投递到交换机！消息ID：{}", correlationData.getId());
                System.out.println("消息成功投递到交换机");
            } else {
                // nack
                log.error("消息投递到交换机失败！消息ID：{}", correlationData.getId());
            }
        }, throwable -> {
            // 失败回调
            log.error("消息发送失败：{}", throwable);
            // 重发消息
        });
        rabbitTemplate.convertAndSend("amq.topic", "simple.queue", message);
//        rabbitTemplate.convertAndSend(exchangeName, "", message);
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
