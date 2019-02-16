package com.yinfeixing.video.response.app.video;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.video.dto.app.client.ClientVideoDto;

public class ClientVideoDetailResponse extends ToString {

    // 视频详情
    private ClientVideoDto clientVideo;

    public ClientVideoDto getClientVideo() {
        return clientVideo;
    }

    public void setClientVideo(ClientVideoDto clientVideo) {
        this.clientVideo = clientVideo;
    }
}
