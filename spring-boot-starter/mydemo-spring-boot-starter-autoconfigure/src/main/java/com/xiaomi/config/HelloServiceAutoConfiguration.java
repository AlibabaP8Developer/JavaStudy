package com.xiaomi.config;

import com.xiaomi.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 容器中没有HelloService时注入
@ConditionalOnMissingBean(HelloService.class)
// 开启属性文件绑定功能，自动就会和HelloProperties中配置的
//      @ConfigurationProperties(value = "hello") 绑定，默认会放到容器中
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
