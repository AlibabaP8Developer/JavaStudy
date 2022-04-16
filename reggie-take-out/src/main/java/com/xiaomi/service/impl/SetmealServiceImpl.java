package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.SetmealDAO;
import com.xiaomi.pojo.Setmeal;
import com.xiaomi.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealDAO, Setmeal> implements SetmealService {
}
