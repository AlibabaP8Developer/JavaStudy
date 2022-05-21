package com.github.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<User> {
}
