package com.yinfeixing.video.response.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

public class RegisteredResponse extends ToString {
    private static final long serialVersionUID = -9029587872729554359L;

    @Remark("用户ID")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}