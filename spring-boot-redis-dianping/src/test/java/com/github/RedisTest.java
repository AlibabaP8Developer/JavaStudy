package com.github;

import com.github.pojo.Shop;
import com.github.service.impl.ShopServiceImpl;
import com.github.utils.RedisCacheUtils;
import com.github.utils.RedisConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private ShopServiceImpl shopService;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    @Test
    public void testSaveShop() throws InterruptedException {
        // shopService.saveShopRedis(1L, 20L);
        Shop shop = shopService.getById(1L);
        redisCacheUtils.setWithLogicalExpire(RedisConstants.CACHE_SHOP_KEY+1L, shop, 10L, TimeUnit.SECONDS);
    }
}
