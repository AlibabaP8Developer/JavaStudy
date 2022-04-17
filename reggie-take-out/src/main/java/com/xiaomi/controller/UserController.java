package com.xiaomi.controller;

import com.xiaomi.common.R;
import com.xiaomi.pojo.User;
import com.xiaomi.service.UserService;
import com.xiaomi.util.SMSUtils;
import com.xiaomi.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        // 获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotBlank(phone)) {
            // 生成随机4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            // 调用阿里云发送短信
            SMSUtils.sendMessage("Li博客", "your", phone, code);
            // 需要将生成的验证码保存到session
            session.setAttribute(phone, code);
        }

        return R.success("手机验证码发送成功！");
    }
}
