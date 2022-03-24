package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.xiaomi.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 第一种方式
// @EnableFeignClients(basePackages = "com.xiaomi.clients") //(defaultConfiguration = DefaultFeignConfig.class)
// 第二种方式
@EnableFeignClients(clients = {UserClient.class}) //(defaultConfiguration = DefaultFeignConfig.class)
@EnableDiscoveryClient
@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 设置负载均衡规则随机
     * @return
     */
    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }
}