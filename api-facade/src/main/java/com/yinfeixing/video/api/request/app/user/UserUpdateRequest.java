package com.cf.api.request.app.user;

import com.cf.api.enums.user.UserGenderEnum;
import com.cf.entity.ToString;
import com.cf.utils.validate.Remark;

public class UserUpdateRequest extends ToString{
    private static final long serialVersionUID = 5304996517735983075L;

    @Remark("用户名称")
    private  String userName;

    @Remark("用户昵称")
    private  String nickName;

    @Remark("用户性别")
    private UserGenderEnum userSex;

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

    public UserGenderEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserGenderEnum userSex) {
        this.userSex = userSex;
    }
}
