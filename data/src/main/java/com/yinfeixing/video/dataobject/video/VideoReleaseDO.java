package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

import java.util.Date;

public class VideoReleaseDO extends DOBaseEntity {
    private Integer videoReleaseId;

    private Long videoId;

    private Date releaseDate;

    private String zoneName;

    private String categoryStatus;

    public Integer getVideoReleaseId() {
        return videoReleaseId;
    }

    public void setVideoReleaseId(Integer videoReleaseId) {
        this.videoReleaseId = videoReleaseId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus == null ? null : categoryStatus.trim();
    }
}