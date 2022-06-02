package com.github.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.common.BaseContext;
import com.github.common.R;
import com.github.common.UserEnums;
import com.github.pojo.User;
import com.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhenghang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody User user, HttpServletRequest request) {
        /*
            1.将页面提交的密码进行md5加密处理
            2.根据页面提交的用户名查询数据库
            3.如果没有查询到则返回登陆失败结果
            4.密码比较时，如果不一致则返回登陆失败结果
            5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
            6.登陆成功，将员工ID存入session并返回登陆成功结果
         */
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User emp = userService.getOne(queryWrapper);
        if (emp == null) {
            return R.error(UserEnums.NOTPASSWORD.getMsg());
        }
        if (!emp.getPassword().equals(password)) {
            return R.error(UserEnums.NOTPASSWORD.getMsg());
        }
        if (emp.getStatus() == UserEnums.LOCK.getCode()) {
            return R.error(UserEnums.LOCK.getMsg());
        }
        request.getSession().setAttribute("user", emp.getId());
        return R.success(emp);
    }

    @GetMapping("/getUserId")
    public R<User> getUserId() {
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        return R.success(user);
    }
}
