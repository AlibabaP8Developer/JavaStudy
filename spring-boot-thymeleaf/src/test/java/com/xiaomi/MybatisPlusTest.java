package com.xiaomi;

import com.xiaomi.dao.UserDAO;
import com.xiaomi.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSelect(){
        List<User> users = userDAO.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("朱元璋");
        user.setAddress("金陵");
        user.setBirthday(LocalDate.now());
        user.setCreateDate(LocalDateTime.now());
        userDAO.insert(user);
    }
}