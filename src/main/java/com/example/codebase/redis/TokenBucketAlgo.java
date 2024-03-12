package com.example.codebase.redis;

import java.time.Instant;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
//
//2.change to java date util
public class TokenBucketAlgo {
    public static boolean limitChecking(String userId, Integer intervalInSeconds, Integer maximumRequests){
        JedisPooled  jedis = new JedisPooled("localhost", 6379);

        if (jedis.get(userId+"_lastRequest") == null){
            jedis.setex(userId+"_counter", intervalInSeconds, String.valueOf(maximumRequests - 1));
            jedis.setex(userId + "_lastRequest", intervalInSeconds, String.valueOf(Instant.now().getEpochSecond()));
        } else {
            var token = jedis.decr(userId+"_counter");
            System.out.print(token);
            return token >= 0;
        }
        return true;
    }
}
