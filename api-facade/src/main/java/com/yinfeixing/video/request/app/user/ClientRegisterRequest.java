package com.yinfeixing.video.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;

public class ClientRegisterRequest extends ToString {

    private static final long serialVersionUID = 4002607122959490987L;

    @Remark("账号类型")
    @NotNull(message = "账号类型不为空")
    private String accountType;

    @Remark("注册账号：目前只支持手机号、邮箱")
    @NotNull(message = "账号不为空")
    private String account;

    @Remark("短信验证码")
    private String smsCode;

    @Remark("密码")
    private String password;

    @Remark("验证码")
    private String identifyCode;

    @Remark("微信凭证")
    private String thirdCert;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getThirdCert() {
        return thirdCert;
    }

    public void setThirdCert(String thirdCert) {
        this.thirdCert = thirdCert;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }
}
