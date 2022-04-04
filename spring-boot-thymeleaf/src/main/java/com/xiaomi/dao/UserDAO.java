package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseMapper<User> {

    /**
     * 自定义分页对象
     * @return
     */
    Page<User> selectPageVo(Page<User> page, String id);
}
