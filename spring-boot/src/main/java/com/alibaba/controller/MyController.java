package com.alibaba.controller;

import com.alibaba.bean.Car;
import com.alibaba.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private Person person;

    @RequestMapping("/test")
    public Person test() {
        return person;
    }
}
