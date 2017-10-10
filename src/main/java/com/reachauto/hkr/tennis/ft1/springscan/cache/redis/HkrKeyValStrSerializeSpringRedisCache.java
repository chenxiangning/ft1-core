package com.reachauto.hkr.tennis.ft1.springscan.cache.redis;

import com.reachauto.hkr.tennis.ft1.springscan.cache.HkrKeyValCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:28
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class HkrKeyValStrSerializeSpringRedisCache implements HkrKeyValCache {
    @Autowired
    @Qualifier(value = "strRedisTemplate")
    private RedisTemplate<String, Object> strRedisTemplate;

    /**
     * 获取缓存 to String
     *
     * @param key 1
     * @return String 1
     */
    @Override
    public String get(Key key) {
        return (String) strRedisTemplate.opsForValue().get(key.getTrueKey());
    }

    /**
     * 获取缓存 to Obj
     *
     * @param key 1
     * @return Obj 1
     */
    @Override
    public <T> T getObject(Key key) {
        return (T) strRedisTemplate.opsForValue().get(key.getTrueKey());
    }

    /**
     * 设置缓存 String
     *
     * @param key 1
     * @param value 1
     * @param cacheSeconds 超时时间 0为无限
     * @return s
     */
    @Override
    public String set(Key key, String value, int cacheSeconds) {
        strRedisTemplate.opsForValue().set(key.getTrueKey(), value, cacheSeconds, TimeUnit.SECONDS);
        return "OK";
    }

    /**
     * 设置缓存 String
     *
     * @param key 1
     * @param value 1
     * @return ok
     */
    @Override
    public String set(Key key, String value) {
        strRedisTemplate.opsForValue().set(key.getTrueKey(), value);
        return "OK";
    }

    /**
     * 设置缓存 Obj
     *
     * @param key 1
     * @param value 1
     * @param cacheSeconds 超时时间 0为无限
     * @return ok
     */
    @Override
    public String setObject(Key key, Object value, int cacheSeconds) {
        strRedisTemplate.opsForValue().set(key.getTrueKey(), value, cacheSeconds, TimeUnit.SECONDS);
        return "OK";
    }

    /**
     * 设置缓存 Obj
     *
     * @param key 1
     * @param value 1
     * @return ok
     */
    @Override
    public String setObject(Key key, Object value) {
        strRedisTemplate.opsForValue().set(key.getTrueKey(), value);
        return "OK";
    }

    /**
     * 设置key的过期时间
     *
     * @param key 1
     * @param sec 1
     * @return ok
     */
    @Override
    public Boolean expire(Key key, long sec) {
        return strRedisTemplate.expire(key.getTrueKey(), sec, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     *
     * @param key 1
     * @return 1
     */
    @Override
    public long del(Key key) {
        strRedisTemplate.delete(key.getTrueKey());
        return 1;
    }

    /**
     * 删除缓存
     *
     * @param key 1
     * @return 1
     */
    @Override
    public long delObject(Key key) {
        strRedisTemplate.delete(key.getTrueKey());
        return 1;
    }

    @Override
    public Set keys(String pattern) {
        return strRedisTemplate.keys(pattern);
    }

    @Override
    public long getExpire(Key key) {
        return strRedisTemplate.getExpire(key.getTrueKey());
    }

    @Override
    public long multiDel(Key key) {
        Object result = strRedisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.delete(key.getTrueKey());
                List<Object> objectList = operations.exec();
                return objectList.get(0);
            }
        });

        return (Long) result;
    }

    /**
     * 判断key是否存在
     *
     * @param key 1
     * @return 1
     */
    @Override
    public boolean hasKey(String key) {
        return strRedisTemplate.hasKey(key);
    }
}
