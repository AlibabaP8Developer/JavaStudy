package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.OrderDetailDAO;
import com.xiaomi.dao.OrdersDAO;
import com.xiaomi.pojo.OrderDetail;
import com.xiaomi.pojo.Orders;
import com.xiaomi.service.OrderDetailService;
import com.xiaomi.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDAO, OrderDetail> implements OrderDetailService {
}
