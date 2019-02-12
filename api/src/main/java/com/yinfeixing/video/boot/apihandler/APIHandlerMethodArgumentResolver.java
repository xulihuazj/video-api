/*
 * APIHandlerMethodArgumentResolver.java 1.0.0 2017/7/30  下午3:25
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/7/30  下午3:25 created by yinqiang
 */
package com.yinfeixing.video.boot.apihandler;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

@Component
public class APIHandlerMethodArgumentResolver extends ServletModelAttributeMethodProcessor {
    /**
     * if "true", non-simple method arguments and
     * return values are considered model attributes with or without a
     * {@code @ModelAttribute} annotation
     */
    public APIHandlerMethodArgumentResolver() {
        super(false);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(APIConverterRequest.class);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        APIDataBinder apiDataBinder = new APIDataBinder(servletBinder.getTarget());
        apiDataBinder.bind(servletRequest);
    }

}
