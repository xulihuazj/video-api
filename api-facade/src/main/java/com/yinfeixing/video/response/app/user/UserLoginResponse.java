package com.yinfeixing.video.response.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;
import com.yinfeixing.video.dto.app.user.UserInfoDTO;

/**
 *  移动端用户登录成功
 */
public class UserLoginResponse  extends ToString {
    private static final long serialVersionUID = -7149303450569616990L;

    @Remark("用户信息")
    private UserInfoDTO userInfoDTO;

    @Remark("用户token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }
}
