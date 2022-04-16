package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishDAO extends BaseMapper<Dish> {
}
