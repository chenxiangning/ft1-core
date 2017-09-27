package com.reachauto.hkr.tennis.ft1.springscan.cache.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 21:50
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface HkrCache {
    String DEFAULT_PREFIX_KEY = "hkr:cache:";

    /**
     * 自定义缓存Key，支持表达式
     *
     * @return String 自定义缓存Key
     */
    String key();

    /**
     * 缓存的过期时间，单位：秒，如果为0则表示永久缓存
     *
     * @return 时间
     */
    int expire() default 60;

}
