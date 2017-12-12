package com.reachauto.hkr.tennis.ft1.springscan.cache.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/27 22:01
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface HkrCacheDel {


    /**
     * 缓存模块
     *
     * @return s
     */
    String model();

    /**
     * 要删除的key,它的格式参照生成缓存的key去设置.最后给个*号
     *
     * @return s[]
     */
    String[] delKey();

    /**
     * 是否删除model下的全部key
     * @return
     */
    boolean isModelAll() default false;


}
