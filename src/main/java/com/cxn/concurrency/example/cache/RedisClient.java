package com.cxn.concurrency.example.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-23 20:34
 * @Version v1.0
 */
@Component
public class RedisClient {

    @Resource(name="redisPool")
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception{
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }


}
