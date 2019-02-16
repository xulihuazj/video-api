package com.yinfeixing.video.request.app.video;

import com.yinfeixing.entity.ToString;

import javax.validation.constraints.NotNull;

public class ClientVideoDetailRequest extends ToString {

    @NotNull(message = "视频id不为空")
    private Long videoId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
