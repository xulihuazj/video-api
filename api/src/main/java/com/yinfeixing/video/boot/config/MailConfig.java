package com.yinfeixing.video.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    //代理服务器地址
    @Value("${mail.host}")
    private String mailProxyHost;

    //端口
    @Value("${mail.port}")
    private Integer mailPort;

    //邮箱协议
    @Value("${mail.protocol}")
    private String mailProtocol;

    @Value("${mail.username}")
    private String mailUserName;

    @Value("${mail.password}")
    private String mailPassword;

    public String getMailProxyHost() {
        return mailProxyHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public String getMailProtocol() {
        return mailProtocol;
    }

    public String getMailUserName() {
        return mailUserName;
    }

    public String getMailPassword() {
        return mailPassword;
    }
}
