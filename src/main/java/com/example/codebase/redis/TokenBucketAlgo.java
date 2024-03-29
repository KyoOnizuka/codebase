package com.example.codebase.redis;

import java.time.Instant;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
//
//2.change to java date util
public class TokenBucketAlgo {
    TokenBucketAlgo(){
        this.jedis = new JedisPooled("localhost", 6379);
    }
    JedisPooled  jedis;
    public boolean allow(String userId, Integer intervalInSeconds, Integer maximumRequests){
        jedis.get(userId+"abc");
        if (jedis.get(userId+"_counter") == null){
            refill(userId, intervalInSeconds, maximumRequests);
        } else {
            var token = jedis.decr(userId+"_counter");
            return token >= 0;
        }
        return true;
    }

    private void refill(String key, Integer intervalInSeconds, Integer maximumRequests){
        jedis.setex(key+"_counter", intervalInSeconds, String.valueOf(maximumRequests - 1));
    }
}
