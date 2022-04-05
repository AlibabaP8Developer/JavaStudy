package com.xiaomi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaomi.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // JSON
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSaveUser() throws JsonProcessingException {
        User user = new User("朱厚照", 12);
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:100", json);
        String jsonUser = stringRedisTemplate.opsForValue().get("user:100");
        // 手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println(user1);
    }

    @Test
    public void testSaveUserHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "玄烨");
        stringRedisTemplate.opsForHash().put("user:400", "age", "13");
        stringRedisTemplate.opsForHash().put("user:500", "name", "弘历");
        Object name = stringRedisTemplate.opsForHash().get("user:400", "name");
        Object age = stringRedisTemplate.opsForHash().get("user:400", "age");
        System.out.println("name:" + name + "   ==   age:" + age);// name:玄烨   ==   age:13
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries:  "+entries);// entries:  {name=玄烨, age=13}
    }

}
