package com.yinfeixing.video.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;

/**
 * 第三方登录
 */
public class ThirdLoginRequest extends ToString {
    private static final long serialVersionUID = -4780392570447399887L;

    @Remark("用户类型")
    private String loginType;

    @Remark("第三方登录凭证不能为空")
    @NotNull(message = "登录凭证不能为空")
    private String loginCert;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginCert() {
        return loginCert;
    }

    public void setLoginCert(String loginCert) {
        this.loginCert = loginCert;
    }
}
