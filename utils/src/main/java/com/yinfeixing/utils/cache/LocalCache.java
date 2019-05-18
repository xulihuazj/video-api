package com.yinfeixing.utils.cache;

import com.alibaba.fastjson.TypeReference;
import com.yinfeixing.utils.log.LogHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LocalCache
 *
 * @author liwei
 * @date 16/8/4
 * @description 本地的缓存类, 使用HashMap存储缓存数据
 * 注意:目前此类对于过期缓存使用懒清除机制(即过期的缓存项,再次访问时才会清除并释放内存,在没有访问时不会自动清除),使用时请酌情考虑内存问题。
 */
public class LocalCache implements ICache {
    /**
     * 内部类,用于存放缓存元素及过期时间
     */
    private class CacheObject {
        // 缓存的值
        private Object value;

        //缓存的过期时间
        private Date expireTime;

        public CacheObject(Object value) {
            this.value = value;
        }

        public CacheObject(Object value, Date expireTime) {
            this.value = value;
            this.expireTime = expireTime;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Date expireTime) {
            this.expireTime = expireTime;
        }
    }

    // 用于存放缓存对象
    private static Map<String, CacheObject> cacheMap = new ConcurrentHashMap<>();

    /**
     * 设置一个缓存
     *
     * @param key    缓存key
     * @param object 缓存项
     * @throws IllegalArgumentException
     */
    protected void set(String key, CacheObject object) throws IllegalArgumentException {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("设置缓存时key不能为空!");
        }
        if (object.expireTime != null && object.expireTime.before(new Date())) {
            throw new IllegalArgumentException("缓存的过期时间不能早于当前时间!");
        }

        cacheMap.put(key, object);
    }

    /**
     * 设置一个缓存,无过期时间
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    @Override
    public void set(String key, Object value) {
        set(key, new CacheObject(value));
    }

    /**
     * 设置一个缓存,有固定的过期时间
     *
     * @param key        缓存key
     * @param value      缓存值
     * @param expireTime 过期时间
     */
    @Override
    public void set(String key, Object value, Date expireTime) {
        set(key, new CacheObject(value, expireTime));
    }

    /**
     * 设置一个缓存,使用浮动的过期时间(秒数)
     *
     * @param key          缓存key
     * @param value        缓存值
     * @param expireSecond 多少秒后过期
     */
    @Override
    public void set(String key, Object value, int expireSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expireSecond);
        set(key, new CacheObject(value, calendar.getTime()));
    }

    /**
     * 获取一个缓存项
     *
     * @param key 缓存key
     * @return
     */
    @Override
    public <T> T get(Class<T> t, String key) {
        return get(key);
    }

    @Override
    public <T> T get(TypeReference<T> type, String key) {
        return get(key);
    }

    private <T> T get(String key) {
        T returnObject = null;
        CacheObject cacheObject = cacheMap.get(key);
        if (cacheObject != null) {
            returnObject = (T) cacheObject.getValue();

            // 如果有缓存,但是缓存过期,删除
            if (cacheObject.getExpireTime() != null && cacheObject.getExpireTime().before(new Date())) {
                returnObject = null;
                cacheMap.remove(key);
            }
        }
        return returnObject;
    }

    /**
     * 删除一个缓存项
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        cacheMap.remove(key);
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间(毫秒)
     * @return 是否获取成功
     */
    synchronized public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        String val = get(String.class, lockKey);
        boolean bl = true;
        if (StringUtils.isNotBlank(val) && !"null".equals(val.toLowerCase())) {
            // 已经有对象在使用了，不能加锁
            bl = false;
        } else {
            set(lockKey, requestId, expireTime / 1000);
        }
        LogHelper.error(lockKey + "|加锁" + System.currentTimeMillis() + "^^^^^^^^^^^^^^^^^^^^^^^" + bl);
        return bl;
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        String val = get(String.class, lockKey);
        boolean bl = false;
        if (StringUtils.isBlank(val) || "null".equals(val.toLowerCase())) {
            bl = true;
        } else if (StringUtils.isNotBlank(requestId) && requestId.equals(val)) {
            remove(lockKey);
            bl = true;
        }
        LogHelper.error(lockKey + "|解锁" + System.currentTimeMillis() + "^^^^^^^^^^^^^^^^^^^^^^^" + bl);
        return bl;
    }
}
