package com.yinfeixing.video.boot.auth;

import com.yinfeiixng.video.error.SystemErrorCode;
import com.yinfeiixng.video.exception.BusinessException;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.net.IP;
import com.yinfeixing.video.api.ApplicationContextHolder;
import com.yinfeixing.video.api.LocalContextHolder;
import com.yinfeixing.video.api.enums.system.SystemApiTypeEnum;
import com.yinfeixing.video.api.system.SystemType;
import com.yinfeixing.video.service.privilege.PrivilegeValidService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限验证
 */
@Component
public class PrivilegeInterceptor implements HandlerInterceptor {

    private Logger logger = LogManager.getLogger(PrivilegeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String token = request.getHeader("Authorization");
//        if (token == null) {
//            token = request.getParameter("Authorization");
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        String url = request.getRequestURI();
//        String ip = IP.getIpAddress(request);
//        //如果前端请求的地址出错则抛出 404
//        if (StringUtils.isNotBlank(url) && url.contains("/error")) {
//            LogHelper.error(logger, "请求地址不正确,ip={1},url={1}", ip, url);
//            throw new BusinessException(HttpStatus.FORBIDDEN.value(), SystemErrorCode.URL_NOT_EFFECTIVE);
//        }
//        // 具有@SystemType注解标注的api
//        SystemType annotationSystem = method.getAnnotation(SystemType.class);
//        LogHelper.debug(logger, "拦截器校验权限【PrivilegeInterceptor】,ip={0}, token={1}", ip, token);
//        SystemApiTypeEnum system = SystemApiTypeEnum.INTERNAL;
//        if (annotationSystem != null) {
//            system = SystemApiTypeEnum.valueOf(annotationSystem.value().toUpperCase());
//        }
//        // 注解为common（不需要校验） 和notify（支付异步通知特殊处理） 标志不需要权限校验
//        if (system.getCode().equalsIgnoreCase(SystemApiTypeEnum.INTERNAL.getCode()) || system.getCode().equalsIgnoreCase(SystemApiTypeEnum.EXTERNAL.getCode())) {
//            PrivilegeValidService privilegeValidService = (PrivilegeValidService) ApplicationContextHolder.getContext().getBean(system.getPrivilegeObjectName());
//            privilegeValidService.privilegeValid();
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 清除LocalContextHolder本地线和中的对象
        LocalContextHolder.clearContext();
        ThreadContext.clearAll();
    }
}
