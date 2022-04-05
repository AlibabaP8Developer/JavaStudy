package com.xiaomi;

import com.xiaomi.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSaveUser() {
        redisTemplate.opsForValue().set("user:100", new User("刘邦", 11));
        User o = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println(o);
    }

    @Test
    public void test() {
        // 写入一条string数据
        redisTemplate.opsForValue().set("name", "朱元璋");
        // 获取string数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
