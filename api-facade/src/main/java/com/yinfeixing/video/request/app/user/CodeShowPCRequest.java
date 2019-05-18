package com.yinfeixing.video.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;

public class CodeShowPCRequest extends ToString {

    private static final long serialVersionUID = -9039664137312327711L;

    @Remark("账号")
    @NotNull(message = "account不能为空", groups = Query.class)
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


}
