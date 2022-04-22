package com.xiaomi.controller;

import com.xiaomi.common.BaseContext;
import com.xiaomi.common.R;
import com.xiaomi.pojo.Orders;
import com.xiaomi.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param order
     * @return
     */
    @PostMapping("/submit")
    public R<String> submitOrder(@RequestBody Orders order) {
        Long userId = BaseContext.getCurrentId();
        ordersService.submit(order);
        return R.success("用户下单成功！");
    }
}
