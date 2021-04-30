package com.hkr.architecture.tennis.springscan.cache.redis;

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
 * chenxiangning@rental.com
 */
@Configuration
public class FtRedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(FtRedisConfig.class);

    @Autowired
    private FtRedisCacheProperties redisPropertiesxx;

    public RedisConnectionFactory createRedisConnectionFactory(FtRedisCacheProperties redisProperties, int database) {
        LOGGER.debug("创建 redis  工厂");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setDatabase(database);
        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        jedisConnectionFactory.afterPropertiesSet();

        return jedisConnectionFactory;
    }

    @Bean(name = "jdkRedisTemplate")
    public RedisTemplate<String, Object> jdkRedisTemplate() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabase()));
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplate index {} 初始化完毕",redisPropertiesxx.getDatabase());
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplate")
    public RedisTemplate<String, Object> strRedisTemplate() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabase()));
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplate index {} 初始化完毕",redisPropertiesxx.getDatabase());
        return strRedisTemplate;
    }


    @Bean(name = "jdkRedisTemplateShiro4")
    public RedisTemplate<String, Object> jdkRedisTemplateShiro4() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabaseShiro()));
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplateShiro index {} 初始化完毕", redisPropertiesxx.getDatabaseShiro());
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplateShiro4")
    public RedisTemplate<String, Object> strRedisTemplateShiro4() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabaseShiro()));
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplateShiro index {} 初始化完毕", redisPropertiesxx.getDatabaseShiro());
        return strRedisTemplate;
    }

    @Bean(name = "jdkRedisTemplateAuth12")
    public RedisTemplate<String, Object> jdkRedisTemplateAuth12() {
        RedisTemplate jdkRedisTemplate = new RedisTemplate();
        jdkRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabaseAuth()));
        jdkRedisTemplate.setKeySerializer(new StringRedisSerializer());
        jdkRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplateAuth index {} 初始化完毕", redisPropertiesxx.getDatabaseAuth());
        return jdkRedisTemplate;
    }

    @Bean(name = "strRedisTemplateAuth12")
    public RedisTemplate<String, Object> strRedisTemplateAuth12() {
        RedisTemplate strRedisTemplate = new RedisTemplate();
        strRedisTemplate.setConnectionFactory(createRedisConnectionFactory(redisPropertiesxx, redisPropertiesxx.getDatabaseAuth()));
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setValueSerializer(new StringRedisSerializer());
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        strRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        LOGGER.info("######## RedisConfig:strRedisTemplateAuth index {} 初始化完毕", redisPropertiesxx.getDatabaseAuth());
        return strRedisTemplate;
    }
}
