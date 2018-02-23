package com.reachauto.hkr.tennis.springscan.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:31
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Configuration
public class FtRedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(FtRedisConfig.class);

    @Autowired
    private FtRedisCacheProperties redisProperties;

    @Bean(name = "createRedisConnectionFactory")
    public RedisConnectionFactory createRedisConnectionFactory() {
        LOGGER.debug("创建 redis  工厂");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        LOGGER.debug("redis connection information is :{}", redisProperties);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        return jedisConnectionFactory;
    }

    @Bean(name = "createRedisConnectionFactory4")
    public RedisConnectionFactory createRedisConnectionFactory4() {
        LOGGER.debug("创建 redis  工厂");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        LOGGER.debug("redis connection information is :{}", redisProperties);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setDatabase(redisProperties.getDatabaseShiro4());
        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        return jedisConnectionFactory;
    }

    @Bean(name = "createRedisConnectionFactory12")
    public RedisConnectionFactory createRedisConnectionFactory12() {
        LOGGER.debug("创建 redis  工厂");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        LOGGER.debug("redis connection information is :{}", redisProperties);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setDatabase(redisProperties.getDatabaseAuth12());
        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        return jedisConnectionFactory;
    }
    @Bean(name = "jdkRedisTemplate")
    public RedisTemplate<String, Object> jdkRedisTemplate() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory());
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:jdkRedisTemplate 初始化完毕");
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplate")
    public RedisTemplate<String, Object> strRedisTemplate() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory());
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplate 初始化完毕");
        return strRedisTemplate;
    }


    @Bean(name = "jdkRedisTemplateShiro4")
    public RedisTemplate<String, Object> jdkRedisTemplateShiro4() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory4());
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:jdkRedisTemplate 初始化完毕");
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplateShiro4")
    public RedisTemplate<String, Object> strRedisTemplateAuth4() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory4());
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplate 初始化完毕");
        return strRedisTemplate;
    }

    @Bean(name = "jdkRedisTemplateAuth12")
    public RedisTemplate<String, Object> jdkRedisTemplateAuth12() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory12());
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:jdkRedisTemplate 初始化完毕");
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplateAuth12")
    public RedisTemplate<String, Object> strRedisTemplateAuth12() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory12());
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplate 初始化完毕");
        return strRedisTemplate;
    }
}
