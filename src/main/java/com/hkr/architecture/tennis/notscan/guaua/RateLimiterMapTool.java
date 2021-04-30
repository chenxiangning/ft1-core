package com.hkr.architecture.tennis.notscan.guaua;

import com.google.common.util.concurrent.RateLimiter;
import com.hkr.architecture.tennis.notscan.cache.SimpleCache;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-05-03 09:05
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
@Slf4j
public class RateLimiterMapTool {
    private static final SimpleCache<String, RateLimiter> resourceLimiterMap = new SimpleCache();

    private RateLimiterMapTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * @param resource
     * @param qps
     */
    public static RateLimiter updateResourceQps(String resource, double qps) {

        RateLimiter limiter = resourceLimiterMap.get(resource);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            RateLimiter putByOtherThread = resourceLimiterMap.putIfAbsent(resource, limiter);
            if (putByOtherThread != null) {
                limiter = putByOtherThread;
            }
        }
        limiter.setRate(qps);

        return limiter;
    }

    public static void removeResource(String resource) {
        resourceLimiterMap.remove(resource);
    }

    public static void exit(String resource) {
        //do nothing when use RateLimiter

    }


}
