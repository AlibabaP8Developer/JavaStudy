package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseMapper<User> {

}
