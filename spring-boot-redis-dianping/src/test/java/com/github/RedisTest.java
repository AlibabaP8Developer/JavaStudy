package com.github;

import com.github.pojo.Shop;
import com.github.service.impl.ShopServiceImpl;
import com.github.utils.RedisCacheUtils;
import com.github.utils.RedisConstants;
import com.github.utils.RedisIdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private ShopServiceImpl shopService;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ExecutorService es = Executors.newFixedThreadPool(500);

    @Test
    public void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order:");
                System.out.println("id== " + id);
            }
            latch.countDown();
        };

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }

        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - begin));
    }

    @Test
    public void testSaveShop() throws InterruptedException {
        // shopService.saveShopRedis(1L, 20L);
        Shop shop = shopService.getById(1L);
        redisCacheUtils.setWithLogicalExpire(RedisConstants.CACHE_SHOP_KEY + 1L, shop, 10L, TimeUnit.SECONDS);
    }

    @Test
    public void loadShopData() {
        // 1.查询店铺信息
        List<Shop> list = shopService.list();

        // 2.把店铺分组，按照typeID分组，id一致的放到一个集合
        Map<Long, List<Shop>> map = list.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        // 3.分批完成写入redis
        for (Map.Entry<Long, List<Shop>> entry : map.entrySet()) {
            // 3.1 获取类型id
            Long typeId = entry.getKey();
            String key = "shop:geo:" + typeId;
            // 3.2获取同类型店铺集合
            List<Shop> value = entry.getValue();
            List<RedisGeoCommands.GeoLocation<String>> locations = new ArrayList<>(value.size());
            // 3.3 写入redis  geoadd key 经纬度 member
            for (Shop shop : value) {
                Double x = shop.getX();
                Double y = shop.getY();
                // 一 ：stringRedisTemplate.opsForGeo().add(key, new Point(x, y), shop.getId().toString());
                locations.add(new RedisGeoCommands.GeoLocation<>(shop.getId().toString(), new Point(x, y)));
            }
            // 二
            stringRedisTemplate.opsForGeo().add(key, locations);
        }
    }
}
