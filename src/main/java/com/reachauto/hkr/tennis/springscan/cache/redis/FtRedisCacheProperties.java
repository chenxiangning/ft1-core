/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reachauto.hkr.tennis.springscan.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for Redis.
 *
 * @author Dave Syer
 * @author Christoph Strobl
 * @author Eddú Meléndez
 */
@Configuration
public class FtRedisCacheProperties {

    /**
     * Database index used by the connection factory.
     */
    @Value("${spring.redis.database}")
    protected int database = 0;
    @Value("${spring.redis.auth.database:12}")
    protected int databaseAuth;
    @Value("${spring.redis.shiro.database:4}")
    protected int databaseShiro;

    /**
     * Redis server host.
     */
    @Value("${spring.redis.host}")
    private String host = "localhost";

    /**
     * Login password of the redis server.
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * Redis server port.
     */
    @Value("${spring.redis.port}")
    private int port = 6379;

    /**
     * Connection timeout in milliseconds.
     */
    private int timeout;

    @Autowired
    private Pool pool;

    public int getDatabaseAuth() {
        return databaseAuth;
    }

    public void setDatabaseAuth(int databaseAuth) {
        this.databaseAuth = databaseAuth;
    }

    public int getDatabaseShiro() {
        return databaseShiro;
    }

    public void setDatabaseShiro(int databaseShiro) {
        this.databaseShiro = databaseShiro;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Pool getPool() {
        return this.pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }
    /**
     * Pool properties.
     */
    @Configuration
    public static class Pool {

        /**
         * Max number of "idle" connections in the pool. Use a negative value to indicate
         * an unlimited number of idle connections.
         */
        @Value("${spring.redis.pool.max-idle}")
        private int maxIdle = 8;

        /**
         * Target for the minimum number of idle connections to maintain in the pool. This
         * setting only has an effect if it is positive.
         */
        @Value("${spring.redis.pool.min-idle}")
        private int minIdle = 0;

        /**
         * Max number of connections that can be allocated by the pool at a given time.
         * Use a negative value for no limit.
         */
        @Value("${spring.redis.pool.max-active}")
        private int maxActive = 80;

        /**
         * Maximum amount of time (in milliseconds) a connection allocation should block
         * before throwing an exception when the pool is exhausted. Use a negative value
         * to block indefinitely.
         */
        @Value("${spring.redis.pool.max-wait}")
        private int maxWait = -1;

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }

    }

}
