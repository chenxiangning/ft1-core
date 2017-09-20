package com.tennis.ft1.springscan.cache.redis;

import com.tennis.ft1.springscan.cache.HkrKeyHashCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 23:03
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class HkrKeyHashJdkSerializeSpringRedisCache implements HkrKeyHashCache {
    @Autowired
    @Qualifier(value = "jdkRedisTemplate")
    private RedisTemplate<String, Object> jdkRedisTemplate;

    private HashOperations<String, String, String> hashOperationsStrVal;
    private HashOperations<String, String, Object> hashOperationsObjVal;

    public Map<String, String> getStrValMap(Key key) {
        hashOperationsStrVal = jdkRedisTemplate.opsForHash();
        return hashOperationsStrVal.entries(key.getTrueKey());
    }

    public Map<String, Object> getObjValMap(Key key) {
        hashOperationsObjVal = jdkRedisTemplate.opsForHash();
        return hashOperationsObjVal.entries(key.getTrueKey());
    }

    public String setStrValMap(Key key, Map<String, String> value) {
        hashOperationsStrVal = jdkRedisTemplate.opsForHash();
        hashOperationsStrVal.putAll(key.getTrueKey(), value);
        return "OK";
    }

    public String setObjValMap(Key key, Map<String, Object> value) {
        hashOperationsObjVal = jdkRedisTemplate.opsForHash();
        hashOperationsObjVal.putAll(key.getTrueKey(), value);
        return "OK";
    }


}
