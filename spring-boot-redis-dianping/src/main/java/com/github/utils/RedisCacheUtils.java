package com.github.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 基于stringredistemplate封装一个缓存工具类，满足下列需求：
 * <p>
 * 方法一：将任意Java对象序列化为json并存储在string类型的key中，并且可以设置ttl过期时间
 * <p>
 * 方法二：将任意Java对象序列化为json并存储在string类型的key中，并且可以设置逻辑过期时间，用于处理缓存击穿问题。
 * <p>
 * 方法三：根据指定的key查询缓存，并反序列化为指定类型，利用缓存空值的方式解决缓存穿透问题
 * <p>
 * 方法四：根据指定的key查询缓存，并反序列化为指定类型，需要利用逻辑过期解决缓存击穿问题
 */
@Slf4j
@Component
public class RedisCacheUtils {

    private final StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public RedisCacheUtils(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit timeUnit) {
        try {
            //String jsonStr = JSONUtil.toJsonStr(value);
            String jsonStr = objectMapper.writeValueAsString(value);
            stringRedisTemplate.opsForValue().set(key, jsonStr, time, timeUnit);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit timeUnit) {
        try {
            // 设置逻辑过期
            RedisData redisData = new RedisData();
            redisData.setData(value);
            redisData.setExpireTime(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(time)));

            String jsonStr = objectMapper.writeValueAsString(redisData);
            stringRedisTemplate.opsForValue().set(key, jsonStr, time, timeUnit);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public <R, ID> R s(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit timeUnit) throws JsonProcessingException {
        String key = keyPrefix + id;

        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            return objectMapper.readValue(json, type);
        }

        // 判断是否是null
        if (json != null) {
            return null;
        }

        // 不存在，根据ID查询数据库
        R r = dbFallback.apply(id);

        // 不存在，返回错误
        if (r == null) {
            stringRedisTemplate.opsForValue().set(key, "", 30, TimeUnit.MINUTES);
            return null;
        }

        this.set(key, r, time, timeUnit);
        return r;
    }

    public <R> R queryWithLogicalExpire(String keyPrefix, Long id, Class<R> type) throws JsonProcessingException {
        String key = keyPrefix + id;

        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            return null;
        }

        RedisData redisData = objectMapper.readValue(json, RedisData.class);

        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);

        LocalDateTime expireTime = redisData.getExpireTime();

        if (expireTime.isAfter(LocalDateTime.now())) {
            return r;
        }

        return null;
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
