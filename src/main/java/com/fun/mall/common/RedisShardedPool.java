package com.fun.mall.common;

import com.fun.mall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:27 2018/12/7
 * @ Description：Redis连接池
 * @ Modified By：
 * @ Version: 1.0$
 */
public class RedisShardedPool {
    private static Logger log = LoggerFactory.getLogger(RedisShardedPool.class);

    // ShardedJedis连接池
    private static ShardedJedisPool pool;

    // 最大连接池数
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotal", "20"));

    // jedisPool最大的idle状态实例个数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle", "10"));

    // jedisPool最小的idle状态实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.minIdle", "2"));

    // 先验证实例是否可用，true代表一定可用
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnBorrow", "true"));

    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn", "true"));

    private static String ip1 = PropertiesUtil.getProperty("redis.cluster0.host");
    private static Integer port1 = Integer.parseInt(PropertiesUtil.getProperty("redis.cluster0.port", "6379"));
    private static Integer timeout1 = Integer.parseInt(PropertiesUtil.getProperty("redis.timeout", "2000"));

    private static String ip2 = PropertiesUtil.getProperty("redis.cluster1.host");
    private static Integer port2 = Integer.parseInt(PropertiesUtil.getProperty("redis.cluster1.port", "6380"));
    private static Integer timeout2 = Integer.parseInt(PropertiesUtil.getProperty("redis.timeout", "2000"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        // 连接满时，是否阻塞
        config.setBlockWhenExhausted(true);

        JedisShardInfo info1 = new JedisShardInfo(ip1, port1, timeout1);

        JedisShardInfo info2 = new JedisShardInfo(ip2, port2, timeout2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>(2);
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for (int i = 0; i < 10; i++) {
            jedis.set("key" + i, "value" + i);
        }
        returnResource(jedis);

        System.out.println("The end!");
    }

}
