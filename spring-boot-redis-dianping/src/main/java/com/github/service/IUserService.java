package com.github.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.dto.LoginFormDTO;
import com.github.dto.Result;
import com.github.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaomi
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();
}
