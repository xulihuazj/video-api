/*
 * AppBeanConfig.java 1.0.0 2017年7月5日 下午8:00:53
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017年7月5日 下午8:00:53 created by xulihua
 */
package com.yinfeixing.video.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 各个Bean 初始化
 *
 * @author xulihua
 * @description
 * @date 2017年7月5日下午8:00:53
 */
@Configuration
public class AppBeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    /**
     * 自定义异步调用时，所用的线程池
     *
     * @Description:
     * @author: xulihua
     * @date: 2018/07/20 16:33
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(100);
        // 最大线程大小
        executor.setMaxPoolSize(200);
        // 设置排队队列长度
        executor.setQueueCapacity(50);
        // 线程保活时间（单位：秒）
//        executor.setKeepAliveSeconds(1);
        // 对拒接的任务处理策略
//        executor.setRejectedExecutionHandler();
//        executor.setThreadFactory(new ThreadFactory() {
//            private final AtomicLong index = new AtomicLong(1);
//
//            @Override
//            public Thread newThread(Runnable r) {
//                LogHelper.info("异步处理，当前线程：" + index.getAndIncrement());
//                return new Thread("异步处理，当前逻辑ID：" + index.getAndIncrement());
//            }
//        });
        return executor;
    }
}
