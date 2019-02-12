package com.yinfeixing.video.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${message.sms.sendurl}")
    private String url;

    @Value("${message.sms.sendzhurl}")
    private String zhUrl;

    @Value("${message.sms.account}")
    private String account;

    @Value("${message.sms.password}")
    private String password;

    @Value("${message.sms.key}")
    private String key;

    @Value("${message.sms.secret}")
    private String secret;

    public String getUrl() {
        return url;
    }

    public String getZhUrl() {
        return zhUrl;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }
}
