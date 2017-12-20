package com.reachauto.hkr.tennis.springscan.cache.redis;

import com.reachauto.hkr.tennis.springscan.cache.HkrKeyValCache;
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
 * Date: 2017/9/20 22:30
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class HkrKeyValJdkSerializeSpringRedisCache implements HkrKeyValCache {
    @Autowired
    @Qualifier(value = "jdkRedisTemplate")
    private RedisTemplate<String, Object> jdkRedisTemplate;

    @Override
    public Set keys(String pattern) {
        return jdkRedisTemplate.keys(pattern);
    }

    @Override
    public String get(Key key) {
        return (String) jdkRedisTemplate.opsForValue().get(key.getTrueKey());
    }

    @Override
    public <T> T getObject(Key key) {
        return (T) jdkRedisTemplate.opsForValue().get(key.getTrueKey());
    }

    @Override
    public String set(Key key, String value, int cacheSeconds) {
        jdkRedisTemplate.opsForValue().set(key.getTrueKey(), value, cacheSeconds, TimeUnit.SECONDS);
        return "OK";
    }

    @Override
    public String set(Key key, String value) {
        jdkRedisTemplate.opsForValue().set(key.getTrueKey(), value);
        return "OK";
    }

    @Override
    public String setObject(Key key, Object value, int cacheSeconds) {
        jdkRedisTemplate.opsForValue().set(key.getTrueKey(), value, cacheSeconds, TimeUnit.SECONDS);
        return "OK";
    }

    @Override
    public String setObject(Key key, Object value) {
        jdkRedisTemplate.opsForValue().set(key.getTrueKey(), value);
        return "OK";
    }

    @Override
    public Boolean expire(Key key, long sec) {
        return jdkRedisTemplate.expire(key.getTrueKey(), sec, TimeUnit.SECONDS);
    }

    @Override
    public long del(Key key) {
        jdkRedisTemplate.delete(key.getTrueKey());
        return 1;
    }

    @Override
    public long multiDel(Key key) {
        Object result = jdkRedisTemplate.execute(new SessionCallback() {
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


    @Override
    public long delObject(Key key) {
        jdkRedisTemplate.delete(key.getTrueKey());
        return 1;
    }

    /**
     * 判断key是否存在
     *
     * @param key 1
     * @return 返回
     */
    @Override
    public boolean hasKey(String key) {
        return jdkRedisTemplate.hasKey(key);
    }

    @Override
    public long getExpire(Key key) {
        return jdkRedisTemplate.getExpire(key.getTrueKey());
    }
}
