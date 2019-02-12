package com.yinfeixing.video.boot.auth;


import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.web.WebHelper;
import com.yinfeixing.video.service.ip.IpWhiteService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ip白名单检查
 *
 * @author xulh
 */
@Component
public class IPWhiteInterceptor implements HandlerInterceptor {
    @Resource
    private IpWhiteService ipWhiteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String IP = WebHelper.getRemoteAddr();// 获取用户真实IP
        String url = request.getServletPath();
//        if (!"/".equals(url.substring(url.length() - 1))) {
//            url = url + "/";
//        }
//        boolean bl = ipWhiteService.checkIpWhite(url, IP);
//        if (!bl) {
//            LogHelper.info("IP白名单检查不通过:当前请求url：" + url + ",ip地址:" + IP);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
