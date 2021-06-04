package com.hinsliu.iotapp.biz.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @Description: utils for Redis(Jedis)
 * @author: liuxuanming
 * @date: 2021/03/28 9:58 上午
 */
@Slf4j
public class RedisUtil {

    private String server = "localhost";

    private Integer port = 6379;

    // expire at 1000 seconds later.
    private static final Integer EXPIRE_TIME = 1000;

    private static RedisUtil redisUtil;

    private static Jedis client;

    private RedisUtil() {
        try {
            client = new Jedis(server, port);
            log.info("Connect to REDIS in " + server + " " + port);
            log.info("Redis is running: " + client.ping());
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            client.close();
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    synchronized public static RedisUtil getInstance() {
        if(redisUtil == null) {
            redisUtil = new RedisUtil();
        }
        return redisUtil;
    }

    public void set(String key, String value) {
        client.setex(key, EXPIRE_TIME, value);
        log.warn("REDIS写入键值对{}:{}", key, value);
    }

    public String get(String key) {
        String value = client.get(key);
        log.warn("REDIS读取键值对{}:{}", key, value);
        return value;
    }
}
