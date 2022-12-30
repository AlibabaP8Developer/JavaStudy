package cn.itcast.hotel.mq;

import cn.itcast.hotel.constants.MqContants;
import cn.itcast.hotel.service.IHotelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelListener {

    @Autowired
    private IHotelService hotelService;

    /**
     * 监听酒店增加或修改消息
     */
    @RabbitListener(queues = MqContants.HOTEL_INSERT_QUEUE)
    public void listenerHotelInsertOrUpdate(Long id) {
        hotelService.insertById(id);
    }

    /**
     * 监听酒店删除消息
     */
    @RabbitListener(queues = MqContants.HOTEL_DELETE_QUEUE)
    public void listenerHotelDelete(Long id) {
        hotelService.deleteById(id);
    }
}
