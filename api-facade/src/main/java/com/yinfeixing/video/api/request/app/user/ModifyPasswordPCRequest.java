package com.yinfeixing.video.api.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Edit;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;

public class ModifyPasswordPCRequest extends ToString {

    private static final long serialVersionUID = -176157396170843364L;

    @Remark("旧密码")
    @NotNull(message="旧密码不能为空",groups= Edit.class)
    private String oldPassword;

    @Remark("新密码")
    @NotNull(message="新密码不能为空",groups=Edit.class)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
