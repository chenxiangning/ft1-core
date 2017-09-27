package com.reachauto.hkr.tennis.ft1.springscan.cache.annotation;

import com.reachauto.hkr.tennis.ft1.springscan.cache.redis.Key;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 21:50
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 *
 * 缓存写入和读取的注解,他有2个参数或者说是属性.
 * key 代表缓存的key
 * expire 代表缓存到期的时间它的单位是秒.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface HkrCache {
    String DEFAULT_PREFIX_KEY = new Key().getTrueKey();

    /**
     * 模块
     *
     * @return String
     */
    String model();

    /**
     * 缓存的过期时间，单位：秒，如果为0则表示永久缓存
     *
     * @return 时间
     */
    int expire() default 60;

}
