package com.yinfeixing.video.dataobject.client;

import com.cf.entity.DOBaseEntity;

public class ClientUserInfoDO extends DOBaseEntity {
    private Long userId;

    private String userName;

    private String userSex;

    private String nickName;

    private String userImageHref;

    private String userPhone;

    private String email;

    private String cretType;

    private String cretNum;

    private String userStatus;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserImageHref() {
        return userImageHref;
    }

    public void setUserImageHref(String userImageHref) {
        this.userImageHref = userImageHref == null ? null : userImageHref.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCretType() {
        return cretType;
    }

    public void setCretType(String cretType) {
        this.cretType = cretType == null ? null : cretType.trim();
    }

    public String getCretNum() {
        return cretNum;
    }

    public void setCretNum(String cretNum) {
        this.cretNum = cretNum == null ? null : cretNum.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

}