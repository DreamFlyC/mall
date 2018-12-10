package com.fun.mall.util;

import com.fun.mall.common.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:24 2018/12/7
 * @ Description：Redis连接池工具类
 * @ Modified By：
 * @Version: 1.0$
 */
public class RedisPoolUtil {
    private static Logger log= LoggerFactory.getLogger(RedisPoolUtil.class);

    public static String set(String key,String value){
        Jedis jedis=null;
        String result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.set(key,value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error",key,value,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;

    }

    public static String get(String key){
        Jedis jedis=null;
        String result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;

    }

    public static String setex(String key,int seconds,String value){
        Jedis jedis=null;
        String result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.setex(key,seconds,value);
        } catch (Exception e) {
            log.error("setex key:{},value:{} error",key,value,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;

    }

    public static Long setnx(String key,String value){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.setnx(key,value);
        } catch (Exception e) {
            log.error("setnx key:{},value:{} error",key,value,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long ttl(String key){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key:{} error",key,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key,int seconds){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.expire(key,seconds);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis= RedisPool.getJedis();
            result=jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error",key,e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis=RedisPool.getJedis();
        System.out.println(jedis.ping());
        RedisPoolUtil.set("test1","123");
        RedisPoolUtil.set("test2","123");
        //RedisPoolUtil.get("test");
        //RedisPoolUtil.del("test");
    }
}
