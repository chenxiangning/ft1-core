package com.reachauto.hkr.tennis.ft1.springscan.cache;

import com.reachauto.hkr.tennis.ft1.springscan.cache.redis.Key;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:18
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public interface HkrKeyValCache {
    /**
     * 获取缓存 to String
     *
     * @param key 1
     * @return String 1
     */
    String get(Key key);


    /**
     * 获取缓存 to Obj
     * @param key k
     * @param <T> t
     * @return 获取缓存 to Obj
     */
    <T> T getObject(Key key);

    /**
     * 设置缓存 String
     *
     * @param key 1
     * @param value 1
     * @param cacheSeconds 超时时间 0为无限
     * @return 1
     */
    String set(Key key, String value, int cacheSeconds);

    /**
     * 设置缓存 String
     *
     * @param key 1
     * @param value 1
     * @return 1
     */
    String set(Key key, String value);

    /**
     * 设置缓存 Obj
     *
     * @param key 1
     * @param value 1
     * @param cacheSeconds 超时时间 0为无限
     * @return 1
     */
    String setObject(Key key, Object value, int cacheSeconds);

    /**
     * 设置缓存 Obj
     *
     * @param key 1
     * @param value 1
     * @return 1
     */
    String setObject(Key key, Object value);

    /**
     * 设置key的过期时间
     *
     * @param key 1
     * @param sec 超时时间
     * @return 1
     */
    Boolean expire(Key key, long sec);

    /**
     * 获取key的到期时间
     *
     * @param key 1
     * @return 1
     */
    long getExpire(Key key);

    /**
     * 删除缓存
     *
     * @param key 1
     * @return 1
     */
    long del(Key key);

    /**
     * 删除缓存
     *
     * @param key 1
     * @return 1
     */
    long delObject(Key key);

    /**
     * 判断key是否存在
     *
     * @param key 1
     * @return 1
     */
    boolean hasKey(String key);

    /**
     * keys列表
     *
     * @param pattern 1
     * @return 1
     */
    Set keys(String pattern);

    /**
     * 同步删除
     * @param key
     * @return
     */
    long multiDel(Key key);

}
