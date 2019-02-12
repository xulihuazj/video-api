package com.yinfeixing.video.api.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

/**
 *  移动端登录
 */
public class ClientLoginRequest extends ToString {
    private static final long serialVersionUID = 7829558954065690715L;
    @Remark("用户账号")
    private String mobile;

    @Remark("用户类型")
    private String loginType;

    @Remark("登录凭证不能为空：密码或者验证码")
    private String loginCert;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginCert() {
        return loginCert;
    }

    public void setLoginCert(String loginCert) {
        this.loginCert = loginCert;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
