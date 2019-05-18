package com.yinfeixing.video.request.app.video;

import com.yinfeixing.entity.ToString;

import javax.validation.constraints.NotNull;

public class ClientVideoDetailRequest extends ToString {

    /**
     * 视频id不为空
     */
    @NotNull(message = "视频ID")
    private String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
