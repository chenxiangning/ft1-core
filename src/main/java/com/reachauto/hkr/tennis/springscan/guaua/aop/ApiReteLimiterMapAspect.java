package com.reachauto.hkr.tennis.springscan.guaua.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.reachauto.hkr.tennis.TennisToolException;
import com.reachauto.hkr.tennis.notscan.guaua.RateLimiterMapTool;
import com.reachauto.hkr.tennis.springscan.guaua.annotation.ApiReteLimiterMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/27 22:14
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 * 切面,前置增强型
 */
@Component
@Aspect
@Slf4j
public class ApiReteLimiterMapAspect {

    /**
     * 参数异常会返回4010
     */
    public static final int TIMEOUT = 1000;
    public static final int RETELIMITER_EXCEPTION = 429;
    public static final String RETELIMITER_EXCEPTION_MSG = "请求频率过快";

    public ApiReteLimiterMapAspect() {
        log.info("########## init HkrReteLimiterMapAspect ##########");
    }

    @Before("@annotation(com.reachauto.hkr.tennis.springscan.guaua.annotation.ApiReteLimiterMap)")
    public void bf(JoinPoint joinPoint) throws Throwable {

        ApiReteLimiterMap hkrReteLimiter = getAnnotation(joinPoint, ApiReteLimiterMap.class);

        RateLimiter rateLimiter = RateLimiterMapTool.updateResourceQps(hkrReteLimiter.key(), hkrReteLimiter.qps());
        if (!rateLimiter.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS)) {
            throw new TennisToolException(RETELIMITER_EXCEPTION, RETELIMITER_EXCEPTION_MSG);
        }
    }

    private <T extends Annotation> T getAnnotation(JoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }

}
