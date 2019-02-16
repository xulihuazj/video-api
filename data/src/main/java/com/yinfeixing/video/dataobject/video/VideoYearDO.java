package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoYearDO extends DOBaseEntity {
    private Long videoYearId;

    private Integer yearId;

    private Long videoId;

    private String videoYearStatus;

    public Long getVideoYearId() {
        return videoYearId;
    }

    public void setVideoYearId(Long videoYearId) {
        this.videoYearId = videoYearId;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoYearStatus() {
        return videoYearStatus;
    }

    public void setVideoYearStatus(String videoYearStatus) {
        this.videoYearStatus = videoYearStatus == null ? null : videoYearStatus.trim();
    }
}