package com.fun.mall.common;

import com.fun.mall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 15:48 2018/12/17
 * @ Description：Redis集群
 * @ Modified By：
 * @ Version    : 1.0$
 */
public class RedisClusterPool {
    private static Logger log = LoggerFactory.getLogger(RedisShardedPool.class);

    //jedis集群
    private static JedisCluster jedisCluster;

    // 最大连接池数
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotal", "20"));

    // jedisPool最大的idle状态实例个数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle", "10"));

    // jedisPool最小的idle状态实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.minIdle", "2"));

    // 先验证实例是否可用，true代表一定可用
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnBorrow", "true"));

    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn", "true"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        // 连接满时，是否阻塞
        config.setBlockWhenExhausted(true);

        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster0.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster0.port","6379"))));
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster1.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster1.port","6380"))));
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster2.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster2.port","6381"))));
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster3.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster3.port","6382"))));
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster4.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster4.port","6383"))));
        nodes.add(new HostAndPort(PropertiesUtil.getProperty("redis.cluster5.host"), Integer.parseInt(PropertiesUtil.getProperty("redis.cluster5.port","6384"))));
        jedisCluster=new JedisCluster(nodes);
    }
    static {
        initPool();
    }

    public static JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public static void returnResource(JedisCluster jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (IOException e) {
                log.error("Redis集群释放资源出现异常,{}",e);
            }
        }
    }

    public static void main(String[] args) {
        JedisCluster cluster=RedisClusterPool.getJedisCluster();
        for (int i = 10; i < 100; i++) {
            cluster.set("key" + i, "value" + i);
        }
        RedisClusterPool.returnResource(cluster);

        System.out.println("The end!");
    }

}
