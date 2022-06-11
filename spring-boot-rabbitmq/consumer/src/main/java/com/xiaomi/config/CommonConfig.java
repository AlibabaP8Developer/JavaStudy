package com.xiaomi.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    /**
     * 交换机持久化
     * @return
     */
    @Bean
    public DirectExchange simpleExchange() {
        // 三个参数：交换机名称、是否持久化、当没有queue与其绑定时是否自动删除
        return new DirectExchange("simple.direct", true, false);
    }

    /**
     * 队列持久化
     * @return
     */
    @Bean
    public Queue simpleExchange1() {
        // 使用QueueBuilder构建队列，durable就是持久化的
        return QueueBuilder.durable("simple.queue").build();
    }
}
