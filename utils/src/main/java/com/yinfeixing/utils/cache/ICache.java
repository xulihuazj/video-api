package com.yinfeixing.utils.cache;

import com.alibaba.fastjson.TypeReference;

import java.util.Date;

/**
 * ICache
 *
 * @author liwei
 * @date 16/8/4
 * @description
 */
public interface ICache {
    /**
     * 设置一个缓存,无过期时间
     * @param key       缓存key
     * @param value     缓存值
     */
    void set(String key, Object value);

    /**
     * 设置一个缓存,有固定的过期时间
     * @param key       缓存key
     * @param value     缓存值
     * @param expireTime    过期时间
     */
    void set(String key, Object value, Date expireTime);

    /**
     * 设置一个缓存,使用浮动的过期时间(秒数)
     * @param key       缓存key
     * @param value     缓存值
     * @param expireSecond  多少秒后过期
     */
    void set(String key, Object value, int expireSecond);

    /**
     * 获取一个缓存项
     * @param key   缓存key
     * @return
     */
    <T> T get(Class<T> t, String key);

    /**
     * 获取一个缓存项
     * @param key   缓存key
     * @return
     */
    <T> T get(TypeReference<T> type, String key);

    /**
     * 删除一个缓存项
     * @param key
     */
    void remove(String key);
    
    /**
     * 获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间(毫秒)
     * @return 是否获取成功
     * @author mazy
     */
    boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime);
    
    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     * @author mazy
     */
	public boolean releaseDistributedLock(String lockKey, String requestId);
}
