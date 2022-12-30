package cn.itcast.hotel.constants;

public class MqContants {

    /**
     * 交换机
     */
    public final static String HOTEL_EXCHANGE = "hotel.topic";
    /**
     * 新增和修改的routingKey
     */
    public final static String HOTEL_INSERT_KEY = "hotel.insert.key";
    /**
     * 删除的routingKey
     */
    public final static String HOTEL_DELETE_KEY = "hotel.delete.key";
    /**
     * 监听新增和修改的队列
     */
    public final static String HOTEL_INSERT_QUEUE = "hotel.insert.queue";
    /**
     * 监听删除的队列
     */
    public final static String HOTEL_DELETE_QUEUE = "hotel.delete.queue";

}
