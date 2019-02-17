package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoDirectorDO extends DOBaseEntity {
    private Integer videoDirectorId;

    private Long videoId;

    private String directorName;

    private String directorStatus;

    public Integer getVideoDirectorId() {
        return videoDirectorId;
    }

    public void setVideoDirectorId(Integer videoDirectorId) {
        this.videoDirectorId = videoDirectorId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName == null ? null : directorName.trim();
    }

    public String getDirectorStatus() {
        return directorStatus;
    }

    public void setDirectorStatus(String directorStatus) {
        this.directorStatus = directorStatus == null ? null : directorStatus.trim();
    }
}