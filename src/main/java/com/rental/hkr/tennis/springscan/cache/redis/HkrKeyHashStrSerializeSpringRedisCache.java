package com.rental.hkr.tennis.springscan.cache.redis;

import com.rental.hkr.tennis.springscan.cache.HkrKeyHashCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 23:03
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
@Service
public class HkrKeyHashStrSerializeSpringRedisCache implements HkrKeyHashCache {
    @Autowired
    @Qualifier(value = "strRedisTemplate")
    private RedisTemplate<String, Object> strRedisTemplate;

    private HashOperations<String, String, String> hashOperationsStrVal;
    private HashOperations<String, String, Object> hashOperationsObjVal;

    @Override
    public Map<String, String> getStrValMap(Key key) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        return hashOperationsStrVal.entries(key.getTrueKey());
    }

    public Map<String, Object> getObjValMap(Key key) {
        hashOperationsObjVal = strRedisTemplate.opsForHash();
        return hashOperationsObjVal.entries(key.getTrueKey());
    }

    @Override
    public String setStrValMap(Key key, Map<String, String> value) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        hashOperationsStrVal.putAll(key.getTrueKey(), value);
        return "OK";
    }

    public String setObjValMap(Key key, Map<String, Object> value) {
        hashOperationsObjVal = strRedisTemplate.opsForHash();
        hashOperationsObjVal.putAll(key.getTrueKey(), value);
        return "OK";
    }

    @Override
    public String put(Key key, String hashKey, String value) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        hashOperationsStrVal.put(key.getTrueKey(), hashKey, value);
        return "OK";
    }

    @Override
    public String get(Key key, String hashKey) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        return hashOperationsStrVal.get(key.getTrueKey(), hashKey);
    }

    @Override
    public long deleteHashKey(Key key, Object... hashKey) {
        return strRedisTemplate.opsForHash().delete(key.getTrueKey(), hashKey);
    }

    @Override
    public boolean hasKey(Key key, Object hashKey) {
        return strRedisTemplate.opsForHash().hasKey(key.getTrueKey(), hashKey);
    }

    @Override
    public boolean putIfAbsent(Key key, String hashKey, String value) {
        return strRedisTemplate.opsForHash().putIfAbsent(key.getTrueKey(), hashKey, value);
    }

    /**
     * 把原本的hGetAll操作简化为hGet，也就是说，不再需要遍历hash中的每一个字段，因此即便不能让多个CPU参与运算，
     * 但是却大幅降低了操作数量，所以性能的提升仍然是显著的；
     *
     * @param key 1
     * @param hashKeys 1
     * @return List
     */
    @Override
    public List<String> multiGet(Key key, Collection<String> hashKeys) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        return hashOperationsStrVal.multiGet(key.getTrueKey(), hashKeys);
    }

    @Override
    public Set<String> keys(Key key) {
        hashOperationsStrVal = strRedisTemplate.opsForHash();
        return hashOperationsStrVal.keys(key.getTrueKey());
    }

}
