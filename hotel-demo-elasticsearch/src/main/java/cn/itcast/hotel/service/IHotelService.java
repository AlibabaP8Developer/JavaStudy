package cn.itcast.hotel.service;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IHotelService extends IService<Hotel> {
    void insertById(Long id);

    void deleteById(Long id);

    PageResult search(RequestParams params);
}
