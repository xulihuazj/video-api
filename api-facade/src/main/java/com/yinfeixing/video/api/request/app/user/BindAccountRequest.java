package com.yinfeixing.video.api.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

/**
 *  账号绑定/解绑： 手机号换绑，绑定，微信绑定
 */
public class BindAccountRequest extends ToString {
    private static final long serialVersionUID = 7797324553603540748L;

    @Remark("绑定类型：手机号，微信")
    private String accountType;

    @Remark("账号绑定不可空:手机号(新手机号)或者微信标识,账号解绑可空")
    private  String accountCert;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCert() {
        return accountCert;
    }

    public void setAccountCert(String accountCert) {
        this.accountCert = accountCert;
    }
}
