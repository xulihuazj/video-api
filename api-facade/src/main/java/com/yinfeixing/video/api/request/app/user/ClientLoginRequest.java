package com.cf.api.request.app.user;

import com.cf.api.enums.app.user.LoginTypeEnum;
import com.cf.entity.ToString;
import com.cf.utils.validate.Remark;

/**
 *  移动端登录
 */
public class ClientLoginRequest extends ToString{
    private static final long serialVersionUID = 7829558954065690715L;
    @Remark("用户账号")
    private String mobile;

    @Remark("用户类型")
    private LoginTypeEnum loginType;

    @Remark("登录凭证不能为空：密码或者验证码")
    private String loginCert;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public String getLoginCert() {
        return loginCert;
    }

    public void setLoginCert(String loginCert) {
        this.loginCert = loginCert;
    }
}
