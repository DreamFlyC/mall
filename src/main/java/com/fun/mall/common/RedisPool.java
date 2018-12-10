package com.fun.mall.common;

import com.fun.mall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:27 2018/12/7
 * @ Description：Redis连接池
 * @ Modified By：
 * @Version: 1.0$
 */
public class RedisPool {
    private static Logger log=LoggerFactory.getLogger(RedisPool.class);

    // jedis连接池
    private static JedisPool pool;

    // 最大连接池数
    private static Integer maxTotal= Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotal","20"));

    // jedispool最大的idle状态实例个数
    private static Integer maxIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle","10"));

    // jedispool最小的idle状态实例个数
    private static Integer minIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.minIdle","2"));

    // 先验证实例是否可用，true代表一定可用
    private static Boolean testOnBorrow=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnBorrow","true"));

    private static Boolean testOnReturn=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn","true"));

    private static String ip=PropertiesUtil.getProperty("redis.host");
    private static Integer port=Integer.parseInt(PropertiesUtil.getProperty("redis.port","6379"));
    private static String pass=PropertiesUtil.getProperty("redis.pass");

    private static void initPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        // 连接满时，是否阻塞
        config.setBlockWhenExhausted(true);

        pool=new JedisPool(config,ip,port,1000*2,pass);
    }

    static {
        initPool();
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

}
