package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.DishDAO;
import com.xiaomi.pojo.Dish;
import com.xiaomi.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishDAO, Dish> implements DishService {

}
