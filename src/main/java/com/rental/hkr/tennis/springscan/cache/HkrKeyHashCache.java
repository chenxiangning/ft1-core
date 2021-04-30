package com.rental.hkr.tennis.springscan.cache;

import com.rental.hkr.tennis.springscan.cache.redis.Key;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:18
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
public interface HkrKeyHashCache {

    Map<String, String> getStrValMap(Key key);

    String setStrValMap(Key key, Map<String, String> value);

    String put(Key key, String hashKey, String value);

    String get(Key key, String hashKey);

    long deleteHashKey(Key key, Object... hashKey);

    boolean hasKey(Key key, Object hashKey);

    boolean putIfAbsent(Key key, String hashKey, String value);

    /**
     * 把原本的hGetAll操作简化为hGet，也就是说，不再需要遍历hash中的每一个字段，因此即便不能让多个CPU参与运算，
     * 但是却大幅降低了操作数量，所以性能的提升仍然是显著的；
     *
     * @param key 主key
     * @param hashKeys 子key集合
     * @return 返回vals
     */
    List<String> multiGet(Key key, Collection<String> hashKeys);

    Set<String> keys(Key key);
}
