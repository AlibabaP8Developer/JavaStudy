package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.ShoppingCartDAO;
import com.xiaomi.pojo.ShoppingCart;
import com.xiaomi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDAO, ShoppingCart> implements ShoppingCartService {
}
