package com.xiaomi.clients;

import com.xiaomi.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("user/{id}")
    public User queryById(@PathVariable("id") Long id);
}
