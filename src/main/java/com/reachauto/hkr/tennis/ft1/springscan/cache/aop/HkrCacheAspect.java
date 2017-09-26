package com.reachauto.hkr.tennis.ft1.springscan.cache.aop;

import com.reachauto.hkr.tennis.ft1.ValidatorTool;
import com.reachauto.hkr.tennis.ft1.notscan.gson.GsonTool;
import com.reachauto.hkr.tennis.ft1.springscan.cache.HkrKeyValCache;
import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache;
import com.reachauto.hkr.tennis.ft1.springscan.cache.redis.Key;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 21:54
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Component
@Aspect
public class HkrCacheAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(HkrCacheAspect.class);

    @Autowired
    @Qualifier(value = "hkrKeyValStrSerializeSpringRedisCache")
    private HkrKeyValCache hkrKeyValCache;

    public HkrCacheAspect() {
        LOGGER.info("########## init HkrCacheAspect");
    }

    @Around("@annotation(com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        HkrCache hkrCache = getAnnotation(joinPoint, HkrCache.class);
        String key = getKey(hkrCache.key(), joinPoint.getArgs());

        String cacheJson = hkrKeyValCache.get(new Key(key));

        if (ValidatorTool.isNotNullOrEmpty(cacheJson)) {
            LOGGER.info("get cache，key [{}]", key);
            return GsonTool.jsonToBean(GsonTool.getGsonAll(), cacheJson, getReturnType(joinPoint));
        } else {
            LOGGER.info("get db set cache key [{}] expire [{}]", key, hkrCache.expire());
            Object result = joinPoint.proceed(joinPoint.getArgs());
            if (result == null) {
                LOGGER.error("fail to get data from source，key [{}]", key);
            } else {
                hkrKeyValCache.setObject(new Key(key), GsonTool.objectToAllFieldNullJson(result), hkrCache.expire());
            }
            return result;
        }

    }

    private String getKey(String key, Object[] args) {
        String result = key;
        for (int i = 0; i < args.length; i++) {
            result = result.replace(HkrCache.ARGS + i, String.valueOf(args[i]));
        }
        return result;
    }

    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }

    private Class<?> getReturnType(ProceedingJoinPoint jp) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        return sign.getMethod().getReturnType();
    }
}
