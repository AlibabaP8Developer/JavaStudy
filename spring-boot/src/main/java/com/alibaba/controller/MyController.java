package com.alibaba.controller;

import com.alibaba.bean.Car;
import com.alibaba.bean.Person;
import com.xiaomi.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private Person person;

    @RequestMapping("/test")
    @Log(value =  "test注解log")
    public Person test() {
        return person;
    }
}
