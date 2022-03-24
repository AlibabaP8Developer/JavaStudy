package com.xiaomi.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 配置feign日志第二种方式
 */
public class DefaultFeignConfig {

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }

}
