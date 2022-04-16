package com.xiaomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.pojo.Category;

public interface CategoryService extends IService<Category> {

    void remove(Long id);
}
