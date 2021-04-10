package com.hinsliu.iotapp.biz.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @Description: utils for Redis(Jedis)
 * @author: liuxuanming
 * @date: 2021/03/28 9:58 上午
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private String server = "localhost";

    private Integer port = 6379;

    // expire at 1000 seconds later.
    private static final Integer EXPIRE_TIME = 1000;

    private static RedisUtil redisUtil;

    private static Jedis client;

    private RedisUtil() {
        try {
            client = new Jedis(server, port);
            logger.info("Connect to REDIS in " + server + " " + port);
            logger.info("Redis is running: " + client.ping());
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            client.close();
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
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
    }

    public String get(String key) {
        return client.get(key);
    }
}
