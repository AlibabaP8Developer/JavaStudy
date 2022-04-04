package com.xiaomi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.HotelDao;
import com.xiaomi.pojo.Hotel;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelDao, Hotel> implements IHotelService {
}
