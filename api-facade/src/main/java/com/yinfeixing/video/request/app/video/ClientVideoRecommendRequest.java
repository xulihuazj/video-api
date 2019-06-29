package com.yinfeixing.video.request.app.video;

import com.yinfeixing.entity.ToString;

import javax.validation.constraints.NotNull;

public class ClientVideoRecommendRequest extends ToString {

    /**
     * 推荐类型
     */
    @NotNull(message = "推荐类型不能为空")
    private String recommendVideoType;

    public String getRecommendVideoType() {
        return recommendVideoType;
    }

    public void setRecommendVideoType(String recommendVideoType) {
        this.recommendVideoType = recommendVideoType;
    }
}
