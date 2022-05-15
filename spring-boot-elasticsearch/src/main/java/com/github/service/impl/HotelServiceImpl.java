package com.github.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mapper.HotelMapper;
import com.github.pojo.Hotel;
import com.github.service.IHotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
