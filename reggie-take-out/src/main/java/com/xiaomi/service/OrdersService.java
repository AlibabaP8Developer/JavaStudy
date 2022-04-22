package com.xiaomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.pojo.Orders;

public interface OrdersService extends IService<Orders> {
    void submit(Orders order);
}
