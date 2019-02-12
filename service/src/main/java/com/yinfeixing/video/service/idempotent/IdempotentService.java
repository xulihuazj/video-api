package com.yinfeixing.video.service.idempotent;


/**
 * 幂等分布式锁处理
 * @author mazy
 *
 */
public interface IdempotentService {

    /**
     *
     * @Function: IdempotentService.java
     * @Description: 检查幂等，如果出现并发将会抛出（该操作正在进行中，请勿重复操作）异常
     * @return：void
     * @author: mazy
     * @date: 2018年11月21日 下午6:30:23
     *
     */
    void idempotentVerify(String key,String uuid);

    /**
     *
     * @Function: IdempotentService.java
     * @Description: 解除幂等锁
     * @return：void
     * @author: mazy
     * @date: 2018年11月21日 下午6:34:40
     *
     */
    void deleteIdempotentLock(String key, String uuid);

    /**
     *
     * @Function: IdempotentService.java
     * @Description: 加锁，该方法加锁失败返回false,如果系统异常了会抛出
     * @param:TODO
     * @return：boolean
     * @author: mazy
     * @date: 2018年12月29日 下午5:24:15
     *
     */
    boolean idempotentVerify2(String key,String uuid);

}
