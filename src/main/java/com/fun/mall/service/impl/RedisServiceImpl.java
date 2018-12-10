package com.fun.mall.service.impl;

import com.fun.mall.service.RedisService;
import com.fun.mall.util.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 14:51 2018/12/5
 * @ Description：Redis服务接口实现
 * @ Modified By：
 * @Version: 1.0$
 */
@Service("RedisServiceImpl")
public class RedisServiceImpl implements RedisService {
    /**
     * 添加SortSet型数据
     * @param key
     * @param value
     */
    public void addSortSet(String key, String value) {
        double score = System.currentTimeMillis();
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.zadd(key, value, score);
    }

    /**
     * 获取倒序的SortSet型的数据
     * @param key
     * @return
     */
    public Set<String> getDescSortSet(String key) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        return redisUtil.zrevrange(key, 0, -1);
    }

    /**
     * 删除SortSet型数据
     * @param key
     * @param value
     */
    public void deleteSortSet(String key, String value) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.zrem(key, value);
    }

    /**
     * 批量删除SortSet型数据
     * @param key
     * @param value
     */
    public void deleteSortSetBatch(String key, String[] value) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.zrem(key, value);
    }

    /**
     * 范围获取倒序的SortSet型的数据
     * @param key
     * @return
     */
    public Set<String> getDescSortSetPage(String key,int start, int end) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        return redisUtil.zrevrange(key, start, end);
    }

    /**
     * 获取SortSet型的总数量
     * @param key
     * @return
     */
    public long getSortSetAllCount(String key) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        return redisUtil.zcard(key);
    }

    /**
     * 检查KEY是否存在
     * @param key
     * @return
     */
    public boolean checkExistsKey(String key) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        return redisUtil.exists(key);
    }

    /**
     * 重命名KEY
     * @param oldKey
     * @param newKey
     * @return
     */
    public String renameKey(String oldKey, String newKey) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        return redisUtil.rename(oldKey, newKey);
    }

    /**
     * 删除KEY
     * @param key
     */
    public void deleteKey(String key) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.del(key);
    }

    /**
     * 设置失效时间
     * @param key
     * @param seconds 失效时间，秒
     */
    public void setExpireTime(String key, int seconds) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.expire(key, seconds);
    }

    /**
     * 删除失效时间
     * @param key
     */
    public void deleteExpireTime(String key) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.persist(key);
    }
}
