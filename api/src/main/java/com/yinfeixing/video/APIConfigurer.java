package com.yinfeixing.video;

import com.yinfeixing.video.boot.apihandler.APIHandlerMethodArgumentResolver;
import com.yinfeixing.video.boot.auth.IPWhiteInterceptor;
import com.yinfeixing.video.boot.auth.IdempotentInterceptor;
import com.yinfeixing.video.boot.auth.PrivilegeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import java.util.List;

/**
 * APIConfigurer
 *
 * @author xulh
 * @date 2017/3/30
 * @description
 */
@Configuration
public class APIConfigurer extends WebMvcConfigurationSupport {

    @Resource
    private PrivilegeInterceptor privilegeInterceptor;
    @Resource
    private IPWhiteInterceptor iPWhiteInterceptor;
    @Resource
    private IdempotentInterceptor idempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(privilegeInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**");
        registry.addInterceptor(iPWhiteInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**");
        registry.addInterceptor(idempotentInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/html");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new APIHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);

    }
}
