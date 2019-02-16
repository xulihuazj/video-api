package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoTypeDO extends DOBaseEntity {
    private Long videoTypeId;

    private Long videoId;

    private Integer typeId;

    private String videoTypeStatus;

    public Long getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(Long videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getVideoTypeStatus() {
        return videoTypeStatus;
    }

    public void setVideoTypeStatus(String videoTypeStatus) {
        this.videoTypeStatus = videoTypeStatus == null ? null : videoTypeStatus.trim();
    }
}