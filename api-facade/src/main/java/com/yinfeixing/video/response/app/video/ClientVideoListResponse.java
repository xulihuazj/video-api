package com.yinfeixing.video.response.app.video;

import com.yinfeixing.video.dto.video.VideoDTO;
import com.yinfeixing.video.response.page.PageResponse;

import java.util.List;

public class ClientVideoListResponse extends PageResponse {

    private List<VideoDTO> videoList;

    public List<VideoDTO> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoDTO> videoList) {
        this.videoList = videoList;
    }
}
