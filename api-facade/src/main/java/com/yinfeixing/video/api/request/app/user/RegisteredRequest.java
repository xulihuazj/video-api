package com.cf.api.request.app.user;

import com.cf.entity.ToString;
import com.cf.utils.validate.Remark;

public class RegisteredRequest extends ToString{
    private static final long serialVersionUID = 4002607122959490987L;

    @Remark("注册账号：目前只支持手机号")
    private String mobile;

    @Remark("短信验证码")
    private String smsCode;

    @Remark("密码")
    private String password;

    @Remark("微信凭证")
    private String thirdCert;

    public String getThirdCert() {
        return thirdCert;
    }

    public void setThirdCert(String thirdCert) {
        this.thirdCert = thirdCert;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
