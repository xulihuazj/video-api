package com.yinfeixing.video.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

public class UserUpdateRequest extends ToString {
    private static final long serialVersionUID = 5304996517735983075L;

    @Remark("用户名称")
    private String userName;

    @Remark("用户昵称")
    private String nickName;

    @Remark("用户性别")
    private String userSex;

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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
