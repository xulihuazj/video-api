package com.yinfeixing.video.request.app.video;

import com.yinfeixing.entity.ToString;

public class ClientVideoRecommendRequest extends ToString {

    /**
     * 推荐类型
     */
    private String recommendVideoType;

    public String getRecommendVideoType() {
        return recommendVideoType;
    }

    public void setRecommendVideoType(String recommendVideoType) {
        this.recommendVideoType = recommendVideoType;
    }
}
