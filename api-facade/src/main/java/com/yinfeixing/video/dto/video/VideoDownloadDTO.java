package com.yinfeixing.video.dto.video;

import com.yinfeixing.entity.ToString;

public class VideoDownloadDTO extends ToString {

    private Long videoId;

    private String downloadType;

    private String videoType;

    // MB 大小
    private Double downloadSize;

    // GB 大小
    private String downloadSizeForGb;

    private String videoAddress;

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
        this.downloadType = downloadType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Double getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(Double downloadSize) {
        this.downloadSize = downloadSize;
    }

    public String getDownloadSizeForGb() {
        return downloadSizeForGb;
    }

    public void setDownloadSizeForGb(String downloadSizeForGb) {
        this.downloadSizeForGb = downloadSizeForGb;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }
}
