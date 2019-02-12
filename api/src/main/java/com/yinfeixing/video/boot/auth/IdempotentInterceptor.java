package com.yinfeixing.video.boot.auth;

import com.yinfeiixng.video.common.Constant;
import com.yinfeixing.utils.security.SecurityHelper;
import com.yinfeixing.utils.uuid.UUIDUtil;
import com.yinfeixing.video.api.BaseController;
import com.yinfeixing.video.api.system.Idempotent;
import com.yinfeixing.video.service.idempotent.IdempotentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 
 * 幂等拦载器
 * @author xulh
 *
 */
@Component
public class IdempotentInterceptor extends BaseController implements HandlerInterceptor {
	
	@Resource
	IdempotentService idempotentService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取method带有idempotent的注解
        Idempotent idempotent = method.getAnnotation(Idempotent.class);
        // 判断是否需要幂等校验
        if(idempotent != null){
        	// 获取请求业务参数的内容
        	String biz = super.getBizString(request);
        	if(StringUtils.isNotBlank(biz)){
        		// 生成md5密文
        		String idempotentKey = SecurityHelper.MD5(biz);
        		// 存入request
        		request.setAttribute(Constant.IDEMPOTENT_KEY, idempotentKey);
        		// 获取uuid
        		String uuid = UUIDUtil.getRandomUUID();
        		// 幂等校验，开始分布式锁
        		idempotentService.idempotentVerify(idempotentKey,uuid);
        		// uuid存入request,如果校验抛出异常这里是不会执行的
        		request.setAttribute(Constant.IDEMPOTENT_UUID, uuid);
        	}
        }
		return true; 
	} 
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (request.getAttribute(Constant.IDEMPOTENT_KEY) != null
				&& request.getAttribute(Constant.IDEMPOTENT_UUID) != null) {
			// 删除幂等校验锁
			idempotentService.deleteIdempotentLock(request.getAttribute(Constant.IDEMPOTENT_KEY).toString(),
					request.getAttribute(Constant.IDEMPOTENT_UUID).toString());
		}
    }
    
}
