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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSelect() {
        // 根据ID查询
        User user = userDAO.selectById("1510479549233508353");
        System.out.println(user);
        System.out.println("=====");
        // 根据多个ID查询
        String[] ids = {"1510479682541182978", "1510479813222998017"};
        List<String> id = Arrays.asList(ids);
        List<User> users = userDAO.selectBatchIds(id);
        users.forEach(System.out::println);
        System.out.println("=====");
        Map<String, Object> map = new HashMap<>();
        map.put("username", "朱高炽");
        map.put("address", "北平顺天府");
        List<User> users1 = userDAO.selectByMap(map);
        users1.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("朱百六");
        user.setAddress("安徽凤阳");
        user.setBirthday(LocalDate.now());
        user.setCreateDate(LocalDateTime.now());
        int result = userDAO.insert(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }

    @Test
    public void testDelete() {
//        int result = userDAO.deleteById(1510473734362816513L);
//        System.out.println("result:" + result);

        // 根据map集合中所设置进行删除
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", "朱初一");
//        int result = userDAO.deleteByMap(map);
//        System.out.println(result);

        // 通过多个ID实现批量删除
        String[] ids = {"1510480255859613698", "1510480052125462530"};
        List<String> id = Arrays.asList(ids);
        userDAO.deleteBatchIds(id);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("1510465499790577665");
        user.setUsername("刘邦");
        int result = userDAO.updateById(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }
}