package com.yinfeixing.video.api.response.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

public class RegisteredResponse extends ToString {
    private static final long serialVersionUID = -9029587872729554359L;

    @Remark("用户ID")
    private  String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
