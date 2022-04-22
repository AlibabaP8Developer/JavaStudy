package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaomi.common.R;
import com.xiaomi.pojo.User;
import com.xiaomi.service.UserService;
import com.xiaomi.util.SMSUtils;
import com.xiaomi.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        // 获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotBlank(phone)) {
            // 生成随机4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("验证码：{}", code);
            // 调用阿里云发送短信
            SMSUtils.sendMessage("Li博客", "SMS_139985936", phone, code);
            // 需要将生成的验证码保存到session
            //session.setAttribute(phone, code);
            // 将生成的验证码缓存到redis中，并且设置有效期为5分钟
            stringRedisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        }

        return R.success("手机验证码发送成功！");
    }

    /**
     * 移动端用户登陆
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String, String> map, HttpSession session) {
        log.info(map.toString());

        // 获取手机号
        String phone = map.get("phone");
        // 获取验证码
        String code = map.get("code");

        // 从session中获取保存的验证码
        // Object codeInSession = session.getAttribute(phone);
        // 从redis中获取验证码
        String codeInRedis = stringRedisTemplate.opsForValue().get(phone);
        // 比对
        if (codeInRedis != null && codeInRedis.equals(code)) {
            // 登陆成功
            // 判断当前手机号是否为新用户，如果是新用户自动完成注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                // 新用户
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            // 如果用户登陆成功，删除redis中缓存的验证码
            stringRedisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登陆失败！");
    }
}
