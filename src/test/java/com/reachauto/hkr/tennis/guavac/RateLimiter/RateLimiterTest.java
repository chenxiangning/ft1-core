package com.reachauto.hkr.tennis.guavac.RateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-12-07 16:02
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 * 我们再看看获取令牌的相关方法：
 *
 * double acquire(); // 阻塞直到获取一个许可，返回被限制的睡眠等待时间，单位秒
 *
 * double acquire(int permits); // 阻塞直到获取permits个许可，返回被限制的睡眠等待时间，单位秒
 *
 * boolean tryAcquire();  // 尝试获取一个许可
 *
 * boolean tryAcquire(int permits);  // 尝试获取permits个许可
 *
 * boolean tryAcquire(long timeout, TimeUnit unit);  // 尝试获取一个许可，最多等待timeout时间
 *
 * boolean tryAcquire(int permits, long timeout, TimeUnit unit);  // 尝试获取permits个许可，
 */
public class RateLimiterTest {

    @Test
    public void noReteLimiterTest() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    @Test
    public void withRateLimiter_acquire() {
        Long start = System.currentTimeMillis();
        // 每秒不超过10个任务被提交
        RateLimiter limiter = RateLimiter.create(1d);
        for (int i = 0; i < 10; i++) {
            // 从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求
            limiter.acquire();
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();
        System.out.println(limiter.getRate());
        System.out.println(end - start);

    }


    @Test
    public void withRateLimiter_acquire2() {
        Long start = System.currentTimeMillis();
        // 每秒10个令牌 通常是指QPS
        RateLimiter limiter = RateLimiter.create(10D);
        for (int i = 0; i < 10; i++) {
            // 从RateLimiter拿到指定数量的令牌才允许，该方法会被阻塞直到获取到请求
            limiter.acquire(2);
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(limiter.getRate());

        System.out.println(end - start);

    }


    @Test
    public void withRateLimiter_动态增加速率() {
        Long start = System.currentTimeMillis();
        // 每秒10个令牌 通常是指QPS
        RateLimiter limiter = RateLimiter.create(10D);
        for (int i = 0; i < 28; i++) {
            // 从RateLimiter拿到指定数量的令牌才允许，该方法会被阻塞直到获取到请求
            limiter.acquire();
            limiter.setRate(limiter.getRate() + 2.0D);
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(limiter.getRate());

        System.out.println(end - start);

    }

    @Test
    public void withRateLimiter_tryAcquire() {
        Long start = System.currentTimeMillis();
        // 每秒不超过10个任务被提交
        RateLimiter limiter = RateLimiter.create(10D);
        for (int i = 0; i < 10; i++) {
            // 从RateLimiter 获取许可，如果该许可可以在无延迟下的情况下立即获取得到的话
            if (limiter.tryAcquire()) {
                System.out.println("call execute.." + i);
            }
        }

        Long end = System.currentTimeMillis();
        System.out.println(limiter.getRate());
        System.out.println(end - start);

    }

    @Test
    public void withRateLimiter_tryAcquire2() {
        Long start = System.currentTimeMillis();
        // 每秒不超过10个任务被提交
        RateLimiter limiter = RateLimiter.create(10D);
        for (int i = 0; i < 10; i++) {
            /**
             * 从RateLimiter获取许可如果该许可可以在不超过timeout的时间内获取得到的话，
             * 或者如果无法在timeout 过期之前获取得到许可的话， 那么立即返回false（无需等待）。
             * 该方法等同于tryAcquire(1, timeout, unit)。
             */
            if (limiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                System.out.println("call execute.." + i);
            } else {
                System.out.println("X");
            }
        }

        Long end = System.currentTimeMillis();
        System.out.println(limiter.getRate());
        System.out.println(end - start);

    }
}