/*
 * BaseInterceptor.java 1.0.0 2017/7/16  下午4:32
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/7/16  下午4:32 created by yinqiang
 */
package com.yinfeixing.video.boot.apihandler;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API参数预处理拦截器基类
 *
 * @author yinqiang
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {

    /**
     * 自定义拦截逻辑
     *
     * @param handler
     * @return true拦截, false不拦截;
     */
    private boolean isHandlerMethod(Object handler) {

        // 判断是否应该被拦截
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAPIHandler ignoreAPIHandler = handlerMethod.getMethodAnnotation(IgnoreAPIHandler.class);
        // 标注类IgnoreAPIHandler的 不处理
        return ignoreAPIHandler == null;
    }

    /**
     * 重写preHandle方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isHandlerMethod(handler)) {
            return doHandler(response, runHandler(request, response));
        }
        return super.preHandle(request, response, handler);
    }

    public abstract boolean runHandler(HttpServletRequest request, HttpServletResponse response);

    /**
     * 对处理结果留口子，便于后续扩展
     *
     * @param response
     * @param isInterceptor
     * @throws Exception
     */
    private boolean doHandler(HttpServletResponse response, boolean isInterceptor) throws Exception {
        return isInterceptor;
    }

}
