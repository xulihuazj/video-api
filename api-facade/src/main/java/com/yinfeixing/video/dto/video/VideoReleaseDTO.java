package com.yinfeixing.video.dto.video;

import com.yinfeixing.entity.ToString;

import java.util.Date;

/**
 * 上映时间及其地区
 */
public class VideoReleaseDTO extends ToString {

    private Long videoId;

    private Date releaseDate;

    private String zoneName;

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
        this.zoneName = zoneName;
    }
}
