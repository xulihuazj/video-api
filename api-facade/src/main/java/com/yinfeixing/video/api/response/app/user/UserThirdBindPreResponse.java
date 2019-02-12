package com.yinfeixing.video.api.response.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

public class UserThirdBindPreResponse extends ToString {
    private static final long serialVersionUID = 696800751879337285L;

    @Remark("appId:目前用于微信绑定")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
