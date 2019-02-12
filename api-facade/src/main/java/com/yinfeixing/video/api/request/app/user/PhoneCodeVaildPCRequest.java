package com.yinfeixing.video.api.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.RE;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *  手机换绑短信验证
 */
public class PhoneCodeVaildPCRequest extends ToString {
    private static final long serialVersionUID = -5817774000806777768L;

    @Remark("注册账号：目前只支持手机号")
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = RE.MOBILE,message = "手机号格式不正确")
    private String mobile;

    @Remark("短信验证码")
    @NotNull(message = "短信验证码不能为空")
    private String smsCode;

    @Remark("验证码类型(6:换绑，9：注册)")
    @NotNull(message = "验证码类型不能为空")
    private int codeType;

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

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}
