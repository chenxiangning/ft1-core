package com.rental.hkr.tennis.springscan.cache.aop;

import com.rental.hkr.tennis.springscan.cache.HkrKeyValCache;
import com.rental.hkr.tennis.springscan.cache.annotation.HkrCache;
import com.rental.hkr.tennis.springscan.cache.annotation.HkrCacheDel;
import com.rental.hkr.tennis.springscan.cache.redis.Key;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/27 22:14
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 * 删除的切面,前置增强型
 */
@Component
@Aspect
public class HkrCacheDelAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(HkrCacheDelAspect.class);

    @Autowired
    @Qualifier(value = "hkrKeyValStrSerializeSpringRedisCache")
    private HkrKeyValCache hkrKeyValCache;

    public HkrCacheDelAspect() {
        LOGGER.info("########## init HkrCacheDelAspect ##########");
    }

    @Before("@annotation(com.rental.hkr.tennis.springscan.cache.annotation.HkrCacheDel)")
    public void bf(JoinPoint joinPoint) throws Throwable {

        HkrCacheDel hkrCacheDel = getAnnotation(joinPoint, HkrCacheDel.class);

        String prefixKey = String.format("%s%s", HkrCache.DEFAULT_PREFIX_KEY, hkrCacheDel.model());
        String[] keys = hkrCacheDel.delKey();
        String classname = joinPoint.getSignature().toShortString();
        classname = classname.substring(0, classname.indexOf('.'));


        String finalClassname = classname;
        if (hkrCacheDel.isModelAll()) {
            LOGGER.info("缓存清除:{}:{}", prefixKey, keys);
            Arrays.stream(keys).forEach(zkey ->
                    hkrKeyValCache.keys(String.format("%s:%s", prefixKey, zkey))
                            .forEach(delKey -> hkrKeyValCache.del(new Key(String.valueOf(delKey))))
            );
        } else {
            LOGGER.info("缓存清除:{}:{}.{}", prefixKey, classname, keys);
            Arrays.stream(keys).forEach(zkey ->
                    hkrKeyValCache.keys(String.format("%s:%s.%s", prefixKey, finalClassname, zkey))
                            .forEach(delKey -> hkrKeyValCache.del(new Key(String.valueOf(delKey))))
            );
        }

    }

    private <T extends Annotation> T getAnnotation(JoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }

}
