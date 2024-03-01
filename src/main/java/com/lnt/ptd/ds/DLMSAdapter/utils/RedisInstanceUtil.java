package com.lnt.ptd.ds.DLMSAdapter.utils;

import com.lnt.ptd.ds.DLMSAdapter.configuration.ConfigJson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RedisInstanceUtil {
    public static Jedis createRedisInstance(){
        Map<String,String> redisDetails=new HashMap<>();
        ConfigJson configJson=new ConfigJson();
        redisDetails.put("ip",configJson.baseConnection().getConnections().getRedisConfig().getIp());
        redisDetails.put("port",configJson.baseConnection().getConnections().getRedisConfig().getPort());
        System.out.println("Redis details loaded" + redisDetails.toString());
        JedisPoolConfig jedisPoolConfig =  new JedisPoolConfig();
        jedisPoolConfig.getBlockWhenExhausted();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(-1));
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMinIdle(10);

        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig,
                redisDetails.getOrDefault("ip", "127.0.0.1"),
                Integer.parseInt(redisDetails.getOrDefault("port", "6379")),
                50000)) {

            return jedisPool.getResource();
        }
    }
}
