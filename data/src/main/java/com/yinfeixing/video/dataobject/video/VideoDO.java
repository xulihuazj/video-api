package com.yinfeixing.video.dataobject.video;

import com.yinfeixing.entity.DOBaseEntity;

public class VideoDO extends DOBaseEntity {
    private Long videoId;

    private String videoName;

    private String videoObjectId;

    private Long videoUpOfUser;

    private String videoType;

    private Integer playSum;

    private Integer commentSum;

    private Integer collectSum;

    private Integer supportSum;

    private Integer opposeSum;

    private Integer videoLength;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoObjectId() {
        return videoObjectId;
    }

    public void setVideoObjectId(String videoObjectId) {
        this.videoObjectId = videoObjectId == null ? null : videoObjectId.trim();
    }

    public Long getVideoUpOfUser() {
        return videoUpOfUser;
    }

    public void setVideoUpOfUser(Long videoUpOfUser) {
        this.videoUpOfUser = videoUpOfUser;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType == null ? null : videoType.trim();
    }

    public Integer getPlaySum() {
        return playSum;
    }

    public void setPlaySum(Integer playSum) {
        this.playSum = playSum;
    }

    public Integer getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(Integer commentSum) {
        this.commentSum = commentSum;
    }

    public Integer getCollectSum() {
        return collectSum;
    }

    public void setCollectSum(Integer collectSum) {
        this.collectSum = collectSum;
    }

    public Integer getSupportSum() {
        return supportSum;
    }

    public void setSupportSum(Integer supportSum) {
        this.supportSum = supportSum;
    }

    public Integer getOpposeSum() {
        return opposeSum;
    }

    public void setOpposeSum(Integer opposeSum) {
        this.opposeSum = opposeSum;
    }

    public Integer getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Integer videoLength) {
        this.videoLength = videoLength;
    }
}