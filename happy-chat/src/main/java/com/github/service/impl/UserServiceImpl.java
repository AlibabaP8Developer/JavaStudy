package com.github.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dao.UserDAO;
import com.github.pojo.User;
import com.github.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {

}
