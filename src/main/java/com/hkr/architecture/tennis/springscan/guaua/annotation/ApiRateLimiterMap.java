package com.hkr.architecture.tennis.springscan.guaua.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/27 22:01
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface ApiRateLimiterMap {


    /**
     * 设置需要限流的接口名称
     *
     * @return s
     */
    String key();

    /**
     * 每秒并发量
     */
    double qps() default 100D;

}
