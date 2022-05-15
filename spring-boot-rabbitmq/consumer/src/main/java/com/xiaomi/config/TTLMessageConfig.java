package com.xiaomi.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TTLMessageConfig {

    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Queue ttlQueue() {
        return QueueBuilder.durable("ttl.queue")
                .ttl(10000).deadLetterExchange("dl.direct")
                .deadLetterRoutingKey("dl")
                .build();
    }

    @Bean
    public Binding ttlBingding() {
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("ttl");
    }

}
