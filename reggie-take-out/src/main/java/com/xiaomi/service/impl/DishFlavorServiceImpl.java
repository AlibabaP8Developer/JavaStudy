package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.DishFlavorDAO;
import com.xiaomi.pojo.DishFlavor;
import com.xiaomi.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorDAO, DishFlavor> implements DishFlavorService {

}
