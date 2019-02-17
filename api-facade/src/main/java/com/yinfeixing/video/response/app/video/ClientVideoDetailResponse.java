package com.yinfeixing.video.response.app.video;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.video.dto.app.client.ClientVideoDTO;

public class ClientVideoDetailResponse extends ToString {

    // 视频详情
    private ClientVideoDTO clientVideo;

    public ClientVideoDTO getClientVideo() {
        return clientVideo;
    }

    public void setClientVideo(ClientVideoDTO clientVideo) {
        this.clientVideo = clientVideo;
    }
}
