package com.xiaomi.service;

import com.xiaomi.config.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认不要放在容器中
 */
public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String hello(String username) {
        return helloProperties.getPrefix() + "::" + username + "::" + helloProperties.getSuffix();
    }
}
