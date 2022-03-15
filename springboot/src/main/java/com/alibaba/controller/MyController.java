package com.alibaba.controller;

import com.alibaba.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private Car car;

    @RequestMapping("/test")
    public Car test() {
        return car;
    }
}
