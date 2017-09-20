package com.tennis.ft1.springscan.cache;

import com.tennis.ft1.springscan.cache.redis.Key;

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
     * @param key
     * @return String
     */
    String get(Key key);

    /**
     * 获取缓存 to Obj
     *
     * @param key
     * @return Obj
     */
    <T> T getObject(Key key);

    /**
     * 设置缓存 String
     *
     * @param key
     * @param value
     * @param cacheSeconds 超时时间 0为无限
     * @return
     */
    String set(Key key, String value, int cacheSeconds);

    /**
     * 设置缓存 String
     *
     * @param key
     * @param value
     * @return
     */
    String set(Key key, String value);

    /**
     * 设置缓存 Obj
     *
     * @param key
     * @param value
     * @param cacheSeconds 超时时间 0为无限
     * @return
     */
    String setObject(Key key, Object value, int cacheSeconds);

    /**
     * 设置缓存 Obj
     *
     * @param key
     * @param value
     * @return
     */
    String setObject(Key key, Object value);

    /**
     * 设置key的过期时间
     *
     * @param key
     * @return
     */
    Boolean expire(Key key, long sec);

    /**
     * 获取key的到期时间
     * @param key
     * @return
     */
    long getExpire(Key key);

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    long del(Key key);

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    long delObject(Key key);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * keys列表
     * @param pattern
     * @return
     */
    Set keys(String pattern);


    long multiDel(Key key);

}
