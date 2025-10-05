package com.stan.stancommons.util;

import com.systemspecs.remita.extended.utils.RedisUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManagerUtil {
    private final RedisUtility redisUtility;

    public void saveToCache(String key, Object value, long expTime) {
        try {
            redisUtility.saveObjectToRedis(key, value,expTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveToCache(String key, Object value) {
        try {
            redisUtility.saveObjectToRedis(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getObjectFromCache(String key) {
        try {
            return redisUtility.getObjectFromRedis(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getObjectFromCache(String key, Class<T> clazz) {
        try {
            return redisUtility.getObjectFromRedis(key, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
