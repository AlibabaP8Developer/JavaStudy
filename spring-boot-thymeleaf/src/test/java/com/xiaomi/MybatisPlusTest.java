package com.xiaomi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.dao.MeetingDAO;
import com.xiaomi.dao.UserDAO;
import com.xiaomi.enums.UserEnum;
import com.xiaomi.pojo.MeetingBasicInfo;
import com.xiaomi.pojo.User;
import com.xiaomi.service.MeetingService;
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

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingDAO meetingDAO;

    /**
     * mybatis plus分页
     */
    @Test
    public void mybatisPlusPage() {
        Page<MeetingBasicInfo> page = new Page<>(1, 5);
        QueryWrapper<MeetingBasicInfo> queryWrapper = new QueryWrapper<>();
        Page<MeetingBasicInfo> meetingBasicInfoPage = meetingDAO.selectPage(page, queryWrapper);
        System.out.println("getTotal：" + meetingBasicInfoPage.getTotal());
        System.out.println("getSize：" + meetingBasicInfoPage.getSize());
        System.out.println("getCountId：" + meetingBasicInfoPage.getCountId());
        System.out.println("getMaxLimit：" + meetingBasicInfoPage.getMaxLimit());
        System.out.println("getPages：" + meetingBasicInfoPage.getPages());
        System.out.println("getCountId：" + meetingBasicInfoPage.getCountId());
        System.out.println("getOrders：" + meetingBasicInfoPage.getOrders());
        System.out.println("getCurrent:" + meetingBasicInfoPage.getCurrent());
        System.out.println("是否有下一页 hasNext:" + meetingBasicInfoPage.hasNext());
        System.out.println("是否有上一页 hasPrevious:" + meetingBasicInfoPage.hasPrevious());
        System.out.println("getRecords："+meetingBasicInfoPage.getRecords());
    }

    @Test
    public void testSelect2() {
        int count = meetingService.count();
        System.out.println("总记录数是：" + count);
    }

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
        Integer count = userDAO.selectCount(null);
        System.out.println("总数：" + count);
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
        user.setSex(UserEnum.FEMAlE.getSex());
        int result = userDAO.updateById(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }
}