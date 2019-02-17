package com.yinfeixing.video.dataobject.client;

import com.yinfeixing.entity.DOBaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "client_user_info")
@EntityListeners(AuditingEntityListener.class)
public class ClientUserInfoDO {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "user_image_href")
    private String userImageHref;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "user_type")
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

}