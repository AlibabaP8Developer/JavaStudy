package com.xiaomi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LazyConfig {

    @Bean
    public Queue lazyQueue() {
        return QueueBuilder.durable("lazy.queue").lazy().build();
    }

    @Bean
    public Queue normalConfig() {
        return QueueBuilder.durable("normal.queue").lazy().build();
    }

}
