package com.reachauto.hkr.tennis.notscan.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-08-02 10:48
 * chenxiangning@reachauto.com
 * Description: redis 分布式锁
 *
 * <blockquote>
 * <h3>使用示例:</h3>
 * </blockquote>
 *
 * <blockquote>
 * RedisDistributLock redisLock = new RedisDistributLock(redisTemplate, "锁头1(keyName)");
 *
 * try {
 *
 * if (redisLock.lock()) {
 *
 * 这块写需要同步执行的业务代码
 * ...
 *
 * }
 *
 * } catch (InterruptedException e) {
 *
 * LOGGER.error("{}", e);
 *
 * redisLock.unlock();
 *
 * Thread.currentThread().interrupt();
 *
 * } finally {
 *
 * redisLock.unlock();
 *
 * }
 *
 * </blockquote>
 */
public class RedisDistributLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributLock.class);
    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
    private RedisTemplate redisTemplate;
    /**
     * Lock key path.
     */
    private String lockKey;

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int expireMsecs = 60000;

    /**
     * 锁等待时间，防止线程饥饿
     */
    private int timeoutMsecs = 40000;

    private volatile boolean locked = false;

    /**
     * 详细的构造函数默认获得超时10000毫秒超时和60000毫秒锁定期满的实例
     *
     * @param redisTemplate redisTemplate
     * @param lockKey       lock key (ex. account:1, ...)
     */
    public RedisDistributLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_LOCK";
    }

    /**
     * @param redisTemplate redisTemplate
     * @param lockKey       要枷锁的key
     * @param timeoutMsecs  超时毫秒数
     */
    public RedisDistributLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        this(redisTemplate, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * @param redisTemplate redisTemplate
     * @param lockKey       要枷锁的key
     * @param timeoutMsecs  超时毫秒数
     * @param expireMsecs   到期毫秒数
     */
    public RedisDistributLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(redisTemplate, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     * @return lock key
     */
    public String getLockKey() {
        return lockKey;
    }

    private String get(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] data = connection.get(serializer.serialize(key));
                    connection.close();
                    if (data == null) {
                        return null;
                    }
                    return serializer.deserialize(data);
                }
            });
        } catch (RuntimeException e) {
            LOGGER.error("get redis error, key : {},{}", key, e);
        }
        return obj != null ? obj.toString() : null;
    }

    private boolean setNX(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (RuntimeException e) {
            LOGGER.error("setNX redis error, key : {},{}", key, e);
        }
        return obj != null ? (Boolean) obj : false;
    }

    private String getSet(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (RuntimeException e) {
            LOGGER.error("setNX redis error, key : {},{}", key, e);
        }
        return obj != null ? (String) obj : null;
    }

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public synchronized boolean lock() throws InterruptedException {

        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            //锁到期时间
            String expiresStr = String.valueOf(expires);
            if (this.setNX(lockKey, expiresStr)) {
                // lock acquired
                locked = true;
                return true;
            }

            //redis里的时间
            String currentValueStr = this.get(lockKey);
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                // lock is expired

                String oldValueStr = this.getSet(lockKey, expiresStr);
                //获取上一个锁到期时间，并设置现在的锁到期时间，
                //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受

                    //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                    // lock acquired
                    locked = true;
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;

            TimeUnit.MILLISECONDS.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
        }
        return false;
    }


    /**
     * Acqurired lock release.
     */
    public synchronized void unlock() {
        if (locked) {
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }
}