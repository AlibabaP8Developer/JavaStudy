package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDAO extends BaseMapper<Category> {

}
