package com.alibaba;

import com.alibaba.bean.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private Car car;

    @Test
    public void contextLoads() {
        System.out.println(car);
    }

}
