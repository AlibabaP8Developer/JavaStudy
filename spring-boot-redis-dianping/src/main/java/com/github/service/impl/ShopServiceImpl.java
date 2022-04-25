package com.github.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.github.dto.Result;
import com.github.pojo.Shop;
import com.github.mapper.ShopMapper;
import com.github.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryById(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        // 1.从redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            // 3.存在，直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return Result.ok(shop);
        }

        // 判断命中的是否是空值 ""
        if (shopJson!=null) {
            //返回错误信息
            return Result.fail("店铺不存在！");
        }

        // 4.不存在，根据ID查询数据库
        Shop shop = getById(id);
        // 5.不存在，返回错误
        if (shop==null) {
            // 将空值写入redis
            stringRedisTemplate.opsForValue().set(key, "",
                    RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            // 返回错误信息
            return Result.fail("店铺不存在！");
        }
        // 6.存在，写入redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop),
                RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        // 返回
        return Result.ok(shop);
    }

    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            return Result.fail("店铺ID不能为空！");
        }
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        // 1 更新数据库
        this.updateById(shop);
        // 2 删除缓存
        stringRedisTemplate.delete(key);
        return Result.ok();
    }
}
