package com.github.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dto.LoginFormDTO;
import com.github.dto.Result;
import com.github.dto.UserDTO;
import com.github.pojo.User;
import com.github.mapper.UserMapper;
import com.github.service.IUserService;
import com.github.utils.RedisConstants;
import com.github.utils.RegexUtils;
import com.github.utils.SystemConstants;
import com.github.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        // 1.校验手机号
        boolean invalid = RegexUtils.isPhoneInvalid(phone);
        // 2.如果不符合，返回错误信息
        if (invalid) {
            return Result.fail("手机号格式错误！");
        }
        // 3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 4.保存验证码到session
        //session.setAttribute("code", code);
        stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + phone,
                code, RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 5.发送验证码
        log.debug("发送验证码成功，验证码：{}", code);
        // 返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 1.校验手机号
        String phone = loginForm.getPhone();
        boolean invalid = RegexUtils.isPhoneInvalid(loginForm.getPhone());
        if (invalid) {
            return Result.fail("手机号格式错误！");
        }
        // 2.校验验证码
        // redis获取验证码
        String codeInRedis = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + phone);
        //String codeInSession = session.getAttribute("code").toString();
        String code = loginForm.getCode();
        if (codeInRedis == null || !codeInRedis.equals(code)) {
            // 3.不一致，报错
            return Result.fail("验证码错误!");
        }

        // 4.一致，根据手机号查询用户
        User user = query().eq("phone", loginForm.getPhone()).one();

        // 5.判断用户是否存在
        if (user == null) {
            // 6.不存在，创建新用户并保存
            user = createUserWithPhone(loginForm.getPhone());
        }

        // 7.保存用户信息session
        // 7.1生成token
        String token = UUID.randomUUID().toString();
        // 7.2将user转为hashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,
                new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> {
                            System.out.println("fieldName:" + fieldName + ", fieldValue:" + fieldValue);
                            return StringUtils.isNotBlank(fieldValue.toString()) ? fieldValue.toString() : "1";
                        }));

        // 7.3保存数据redis
        //session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class));
        String tokenKey = RedisConstants.LOGIN_TOKEN_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        stringRedisTemplate.expire(tokenKey, RedisConstants.LOGIN_TOKEN_TTL, TimeUnit.MINUTES);
        // 8.返回token
        return Result.ok(token);
    }

    @Override
    public Result sign() {
        // 1.获取当前登陆用户
        Long userId = UserHolder.getUser().getId();
        // 2.获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3.拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = RedisConstants.USESR_SIGN_KEY + userId + keySuffix;
        // 4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5.写入redis， setbit key offset
        stringRedisTemplate.opsForValue().setBit(key, dayOfMonth - 1, true);
        return Result.ok();
    }

    private User createUserWithPhone(String phone) {
        // 1.创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(SystemConstants.USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        // 2.保存用户
        this.save(user);
        return user;
    }
}
