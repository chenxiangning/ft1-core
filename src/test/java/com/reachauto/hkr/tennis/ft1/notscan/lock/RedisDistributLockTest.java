package com.reachauto.hkr.tennis.ft1.notscan.lock;

import com.reachauto.hkr.tennis.ft1.AbstractJUnit;
import com.reachauto.hkr.tennis.ft1.springscan.cache.HkrKeyValCache;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisDistributLockTest extends AbstractJUnit {

    @Autowired
    @Qualifier(value = "strRedisTemplate")
    private RedisTemplate restTemplate;

    @Autowired
    @Qualifier(value = "hkrKeyValStrSerializeSpringRedisCache")
    private HkrKeyValCache hkrKeyValCache;


    @Test
    public void t() {

//        RedisDistributLock lock = new RedisDistributLock(restTemplate, "kaka", 1);
//
//        try {
//            if (lock.lock()) {
//                System.out.println(123);
//            }
//        } catch (InterruptedException e) {
//            lock.unlock();
//        } finally {
//            lock.unlock();
//        }

    }

}