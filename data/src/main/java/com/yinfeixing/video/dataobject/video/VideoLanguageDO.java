package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoLanguageDO extends DOBaseEntity {
    private Long videLanguageId;

    private Long videoId;

    private Integer languageId;

    private String videLanguageStatus;

    public Long getVideLanguageId() {
        return videLanguageId;
    }

    public void setVideLanguageId(Long videLanguageId) {
        this.videLanguageId = videLanguageId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getVideLanguageStatus() {
        return videLanguageStatus;
    }

    public void setVideLanguageStatus(String videLanguageStatus) {
        this.videLanguageStatus = videLanguageStatus == null ? null : videLanguageStatus.trim();
    }
}