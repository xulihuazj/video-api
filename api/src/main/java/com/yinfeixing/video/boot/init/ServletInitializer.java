package com.yinfeixing.video.boot.init;

import com.cf.pms.ApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        PropertiesInitializer.init(servletContext);
        super.onStartup(servletContext);

    }

}
