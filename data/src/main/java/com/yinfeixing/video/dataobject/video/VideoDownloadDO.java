package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoDownloadDO extends DOBaseEntity {
    private Long videoAddressId;

    private Long videoId;

    private String downloadType;

    private String videoType;

    private Double downloadSize;

    private String videoAddress;

    private String videoAddressStatus;

    public Long getVideoAddressId() {
        return videoAddressId;
    }

    public void setVideoAddressId(Long videoAddressId) {
        this.videoAddressId = videoAddressId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType == null ? null : downloadType.trim();
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType == null ? null : videoType.trim();
    }

    public Double getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(Double downloadSize) {
        this.downloadSize = downloadSize;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress == null ? null : videoAddress.trim();
    }

    public String getVideoAddressStatus() {
        return videoAddressStatus;
    }

    public void setVideoAddressStatus(String videoAddressStatus) {
        this.videoAddressStatus = videoAddressStatus == null ? null : videoAddressStatus.trim();
    }
}