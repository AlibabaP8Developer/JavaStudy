package com.xiaomi;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(JedisApplication.class, args);
    }

    /**
     * master：从主节点读取
     * master_preferred：优先从master节点读取，master不可用才读取replica
     * replica：从slave（replica）节点读取
     * replica_preferred：优先从slave（replica）节点读取，所有的slave都不可用才读取master
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return (clientConfigurationBuilder) -> clientConfigurationBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}
