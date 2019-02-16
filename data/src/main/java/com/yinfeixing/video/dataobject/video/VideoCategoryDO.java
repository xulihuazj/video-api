package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoCategoryDO extends DOBaseEntity {
    private Long videoCategoryId;

    private Long videoId;

    private Integer categoryId;

    private String videoCategoryStatus;

    public Long getVideoCategoryId() {
        return videoCategoryId;
    }

    public void setVideoCategoryId(Long videoCategoryId) {
        this.videoCategoryId = videoCategoryId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getVideoCategoryStatus() {
        return videoCategoryStatus;
    }

    public void setVideoCategoryStatus(String videoCategoryStatus) {
        this.videoCategoryStatus = videoCategoryStatus == null ? null : videoCategoryStatus.trim();
    }
}