package com.fun.mall.util;

import com.fun.mall.common.RedisShardedPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:24 2018/12/7
 * @ Description：Redis连接池工具类
 * @ Modified By：
 * @Version: 1.0$
 */
public class RedisShardedPoolUtil {
    private static Logger log= LoggerFactory.getLogger(RedisShardedPoolUtil.class);

    public static String set(String key,String value){
        ShardedJedis jedis=null;
        String result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.set(key,value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error",key,value,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;

    }

    public static String get(String key){
        ShardedJedis jedis=null;
        String result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;

    }

    public static String setex(String key,int seconds,String value){
        ShardedJedis jedis=null;
        String result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.setex(key,seconds,value);
        } catch (Exception e) {
            log.error("setex key:{},value:{} error",key,value,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;

    }

    public static Long setnx(String key,String value){
        ShardedJedis jedis=null;
        Long result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.setnx(key,value);
        } catch (Exception e) {
            log.error("setnx key:{},value:{} error",key,value,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static Long ttl(String key){
        ShardedJedis jedis=null;
        Long result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key:{} error",key,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key,int seconds){
        ShardedJedis jedis=null;
        Long result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.expire(key,seconds);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        ShardedJedis jedis=null;
        Long result=null;
        try {
            jedis= RedisShardedPool.getJedis();
            result=jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error",key,e);
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        ShardedJedis jedis=RedisShardedPool.getJedis();
        RedisShardedPoolUtil.set("test1","123");
        RedisShardedPoolUtil.set("test2","123");
        //RedisShardedPoolUtil.get("test");
        //RedisShardedPoolUtil.del("test");
    }
}
