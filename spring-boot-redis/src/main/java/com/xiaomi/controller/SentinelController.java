package com.xiaomi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class SentinelController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {

        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set")
    public String set() {
        stringRedisTemplate.opsForValue().set("age", "13");
        return "ok";
    }
}
