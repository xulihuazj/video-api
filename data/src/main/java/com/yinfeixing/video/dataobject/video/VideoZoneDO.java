package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoZoneDO extends DOBaseEntity {
    private Long videoZoneId;

    private Long videoId;

    private Integer zoneId;

    private String videoZoneStatus;

    public Long getVideoZoneId() {
        return videoZoneId;
    }

    public void setVideoZoneId(Long videoZoneId) {
        this.videoZoneId = videoZoneId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getVideoZoneStatus() {
        return videoZoneStatus;
    }

    public void setVideoZoneStatus(String videoZoneStatus) {
        this.videoZoneStatus = videoZoneStatus == null ? null : videoZoneStatus.trim();
    }
}