package com.reachauto.hkr.tennis.ft1.springscan.cache.aop;

import com.reachauto.hkr.tennis.ft1.ValidatorTool;
import com.reachauto.hkr.tennis.ft1.notscan.gson.GsonTool;
import com.reachauto.hkr.tennis.ft1.springscan.cache.HkrKeyValCache;
import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache;
import com.reachauto.hkr.tennis.ft1.springscan.cache.redis.Key;
import org.apache.commons.lang.StringUtils;
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
 * 缓存的读写切面,环绕型处理.
 */
@Component
@Aspect
public class HkrCacheAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(HkrCacheAspect.class);

    @Autowired
    @Qualifier(value = "hkrKeyValStrSerializeSpringRedisCache")
    private HkrKeyValCache hkrKeyValCache;

    public HkrCacheAspect() {
        LOGGER.info("########## init HkrCacheAspect ##########");
    }

    @Around("@annotation(com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        HkrCache hkrCache = getAnnotation(joinPoint, HkrCache.class);
        Key key = getCacheKey(joinPoint, hkrCache);

        String cacheJson = hkrKeyValCache.get(key);

        if (ValidatorTool.isNotNullOrEmpty(cacheJson)) {
            LOGGER.info("get cache，key [{}]", key.getTrueKey());
            return GsonTool.jsonToBean(GsonTool.getGsonAll(), cacheJson, getReturnType(joinPoint));
        } else {
            LOGGER.info("get db set cache key [{}] expire [{}]", key.getTrueKey(), hkrCache.expire());
            Object result = joinPoint.proceed(joinPoint.getArgs());
            if (result == null) {
                LOGGER.error("fail to get data from source，key [{}]", key.getTrueKey());
            } else {
                hkrKeyValCache.setObject(key, GsonTool.objectToAllFieldNullJson(result), hkrCache.expire());
            }
            return result;
        }

    }

    /**
     * 根据类名、方法名和参数值获取唯一的缓存键
     *
     * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "class.getById(int).123"
     */
    private Key getCacheKey(ProceedingJoinPoint joinPoint, HkrCache hkrCache) {
        String classname = joinPoint.getSignature().toShortString();
        classname = classname.substring(0, classname.indexOf('.'));
        String methodKey = joinPoint.getSignature().toString();
        methodKey = methodKey.substring(methodKey.lastIndexOf('.'));

        return new Key(String.format("%s%s:%s:%s",
                HkrCache.DEFAULT_PREFIX_KEY,
                hkrCache.model(),
                String.format("%s%s", classname, methodKey),
                StringUtils.join(joinPoint.getArgs(), ",")));
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
