package com.cf.api.request;

import com.cf.api.enums.common.Source;
import com.cf.entity.ToString;

public class AbstractRequest extends ToString {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 客户端版本号
     */
    private String version;

    /**
     * 授权令牌
     */
    private String token;

    /**
     * 请求来源（必传)
     */
    private Source source;

    /**
     * 用于接收来源信息
     */
    private String dtMonitor;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDtMonitor() {
        return dtMonitor;
    }

    public void setDtMonitor(String dtMonitor) {
        this.dtMonitor = dtMonitor;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
