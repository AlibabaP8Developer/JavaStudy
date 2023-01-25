package com.github.sharding;

import com.github.sharding.mapper.UserMapper;
import com.github.sharding.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReadwriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 写入数据的测试
     */
    @Transactional//开启事务
    @Test
    public void testInsert(){
        User user = new User();
        user.setId(1);
        user.setTitle("完颜崇厚");
        user.setStatus(1);
        userMapper.insert(user);
    }

    /**
     * 读数据测试
     */
    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectList(null);//执行第二次测试负载均衡
        users.forEach(System.out::println);
    }
}
