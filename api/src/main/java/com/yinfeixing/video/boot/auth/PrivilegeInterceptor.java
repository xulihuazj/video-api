package com.yinfeixing.video.boot.auth;

import com.cf.api.LocalContextHolder;
import com.cf.api.enums.system.SystemApiTypeEnum;
import com.cf.api.system.SystemType;
import com.cf.pms.api.ApplicationContextHolder;
import com.cf.pms.dataconfig.plugin.CompanyHelper;
import com.cf.pms.dataconfig.plugin.CompanyUtil;
import com.cf.pms.dataconfig.plugin.WhiteHelper;
import com.cf.pms.dataconfig.plugin.WhiteUtil;
import com.cf.pms.error.SystemErrorCode;
import com.cf.pms.exception.BusinessException;
import com.cf.pms.service.PrivilegeValidService;
import com.cf.utils.environment.EnvironmentEnum;
import com.cf.utils.log.LogHelper;
import com.cf.utils.net.IP;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            token = request.getParameter("Authorization");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String url = request.getRequestURI();
        String ip = IP.getIpAddress(request);
        //如果前端请求的地址出错则抛出 404
        if (StringUtils.isNotBlank(url) && url.contains("/error")) {
            LogHelper.error(logger, "请求地址不正确,ip={1},url={1}", ip, url);
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), SystemErrorCode.URL_NOT_EFFECTIVE);
        }

        // 具有@SystemType注解标注的api
        SystemType annotationSystem = method.getAnnotation(SystemType.class);
        LogHelper.debug(logger, "拦截器校验权限【PrivilegeInterceptor】,ip={0}, token={1}", ip, token);
        //先判断是不是退出登录或者有没有含有 注解
        //注解为空或者值为 INTERNAL 表示内部系统并且需要权限校验
        // 默认为内部管理系统
        SystemApiTypeEnum system = SystemApiTypeEnum.INTERNAL;
        if (annotationSystem != null) {
            system = SystemApiTypeEnum.valueOf(annotationSystem.value().toUpperCase());
        }
        CompanyHelper.setSystemType(system.getCode());
        // 注解为common（不需要校验） 和notify（支付异步通知特殊处理） 标志不需要权限校验
        if (system.getCode().equalsIgnoreCase(SystemApiTypeEnum.INTERNAL.getCode()) || system.getCode().equalsIgnoreCase(SystemApiTypeEnum.EXTERNAL.getCode())) {
            PrivilegeValidService privilegeValidService = (PrivilegeValidService) ApplicationContextHolder.getContext().getBean(system.getPrivilegeObjectName());
            privilegeValidService.privilegeValid();
        } else {
            WhiteHelper.setWhiteList(true, EnvironmentEnum.COMMON.getCode());
        }

        if (StringUtils.isNotBlank(url) && url.contains("/login")) {
            CompanyHelper.getChildCompanyIds(null);
            WhiteHelper.setWhiteList(true, EnvironmentEnum.COMMON.getCode());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	// 清空 WhitHelper的全局变量
        WhiteUtil.clearLocalWhiteList();
        // 清除CompanyUtil下的本地对象
        CompanyUtil.clearLocalCompanyList();
        // 清除LocalContextHolder本地线和中的对象
        LocalContextHolder.clearContext();
        ThreadContext.clearAll();
    }
}
