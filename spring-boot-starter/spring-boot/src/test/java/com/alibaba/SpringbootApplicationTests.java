package com.alibaba;

import com.xiaomi.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private HelloService car;

    @Test
    public void contextLoads() {
        System.out.println(car.hello("大明：：：：大清"));
    }

}
