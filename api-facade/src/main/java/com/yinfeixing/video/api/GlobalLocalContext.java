package com.yinfeixing.video.api;

import com.yinfeixing.utils.validate.Remark;

/**
 * @author xulh
 * @Description
 * @Date 2017年8月3日下午4:15:34
 */
public class GlobalLocalContext {

    @Remark("用户ID")
    private Long userId;

    @Remark("是否是白名单（是：WHITE,否：GENERAL）")
    private boolean isWhite;

    @Remark("来源")
    private String source;

    @Remark("设备ID")
    private String deviceId;

    @Remark("版本信息")
    private String version;

    @Remark("用于接收来源信息")
    private String dtMonitor;

    @Remark("当前IP")
    private String currentIp;

    @Remark("用户类型")
    private String userType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getDtMonitor() {
        return dtMonitor;
    }

    public void setDtMonitor(String dtMonitor) {
        this.dtMonitor = dtMonitor;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
