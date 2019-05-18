package com.yinfeixing.video.request.app.video;

import com.yinfeixing.entity.ToString;

import javax.validation.constraints.NotNull;

public class DicRequest extends ToString {

    @NotNull(message = "类型不为空")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
