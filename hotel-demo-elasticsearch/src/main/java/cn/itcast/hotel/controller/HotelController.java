package cn.itcast.hotel.controller;

import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    /*
        返回值：PageResult
        long total 总条数
        List<HotelDoc> 酒店数据
     */
    @PostMapping("list")
    public PageResult search(@RequestBody RequestParams params) {
        return hotelService.search(params);
    }
}
