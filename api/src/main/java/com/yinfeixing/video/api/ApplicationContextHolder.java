package com.yinfeixing.video.api;

import com.cf.pms.ApplicationRuntimeContext;
import com.cf.pms.model.system.UserAuthModel;
import com.cf.pms.service.system.TokenHelpService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ApplicationContextHolder
 *
 * @author liwei
 * @date 2017/3/3
 * @description
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private Logger logger = LogManager.getLogger(ApplicationContextHolder.class);

    private static ApplicationContext context;

    @Resource
    private TokenHelpService tokenHelpService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取上下文信息
     *
     * @return
     */
    public ApplicationRuntimeContext getRuntimeContext(String token) {
        ApplicationRuntimeContext runtimeContext = new ApplicationRuntimeContext();
        if (StringUtils.isNotEmpty(token)) {
            runtimeContext.setUserAuthModel(tokenHelpService.getUserModelByToken(token, UserAuthModel.class));
        }
        return runtimeContext;
    }
}
