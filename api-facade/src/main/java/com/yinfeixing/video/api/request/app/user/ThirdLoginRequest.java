package com.cf.api.request.app.user;

import com.cf.api.enums.app.user.LoginTypeEnum;
import com.cf.entity.ToString;
import com.cf.utils.validate.Remark;

import javax.validation.constraints.NotNull;

/**
 *  第三方登录
 */
public class ThirdLoginRequest extends ToString{
    private static final long serialVersionUID = -4780392570447399887L;

    @Remark("用户类型")
    private LoginTypeEnum loginType;

    @Remark("第三方登录凭证不能为空")
    @NotNull(message = "登录凭证不能为空")
    private String loginCert;

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
