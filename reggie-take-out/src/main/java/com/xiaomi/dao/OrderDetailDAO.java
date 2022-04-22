package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.pojo.OrderDetail;
import com.xiaomi.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailDAO extends BaseMapper<OrderDetail> {
}
