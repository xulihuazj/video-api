package com.yinfeixing.video.dto.video;

import com.yinfeixing.entity.ToString;

public class VideoDTO extends ToString {

    private Long videoId;

    // 名称
    private String videoName;

    private String videoObjectId;

    // 上传者
    private Long videoUpOfUser;

    // 类型
    private String videoType;

    // 播放次数
    private Integer playSum;

    // 评论次数
    private Integer commentSum;

    private Integer collectSum;

    // 支持人数
    private Integer supportSum;

    private Integer opposeSum;

    // 长度
    private String videoLength;

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
        this.videoName = videoName;
    }

    public String getVideoObjectId() {
        return videoObjectId;
    }

    public void setVideoObjectId(String videoObjectId) {
        this.videoObjectId = videoObjectId;
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
        this.videoType = videoType;
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

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }
}
