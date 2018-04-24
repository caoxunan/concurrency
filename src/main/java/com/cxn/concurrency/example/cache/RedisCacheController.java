package com.cxn.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-23 20:40
 * @Version v1.0
 */
@Controller
@RequestMapping("/cache")
public class RedisCacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    @ResponseBody
    public String set(String key, String value) throws Exception {
        redisClient.set(key,value);
        return "success";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(String key) throws Exception {
        String result = redisClient.get(key);
        return result;
    }

}
