package com.yinfeixing.video.dto.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

/**
 * UserInfoDTO.java 1.0.0 2018-06-04  16:33
 * 移动端用户信息
 */
public class UserInfoDTO extends ToString {
    private static final long serialVersionUID = 2328426334488995628L;

    @Remark("用户名称")
    private String userName;

    @Remark("用户昵称")
    private String nickName;

    @Remark("用户性别")
    private String userSex;

    @Remark("用户Id")
    private String userId;

    @Remark("用户头像地址")
    private String userImageHref;

    @Remark("用户手机号")
    private String userPhone;

    @Remark("微信用户UUId")
    private String unicode;

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImageHref() {
        return userImageHref;
    }

    public void setUserImageHref(String userImageHref) {
        this.userImageHref = userImageHref;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
