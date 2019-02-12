/*
 * APIHeaderParameterInterceptor.java 1.0.0 2017/7/16  下午4:48
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/7/16  下午4:48 created by yinqiang
 */
package com.yinfeixing.video.boot.apihandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yinqiang
 * 针对api头参数信息拦截处理
 */
public class APIHeaderParameterInterceptor extends BaseInterceptor {

    @Override
    public boolean runHandler(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

}
