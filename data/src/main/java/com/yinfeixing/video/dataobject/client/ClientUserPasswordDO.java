package com.yinfeixing.video.dataobject.client;

import com.yinfeixing.entity.DOBaseEntity;

public class ClientUserPasswordDO extends DOBaseEntity {
    private Long userId;

    private String passwordType;

    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType == null ? null : passwordType.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}