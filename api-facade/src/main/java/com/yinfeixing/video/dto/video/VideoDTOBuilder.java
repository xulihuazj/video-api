package com.yinfeixing.video.dto.video;

public final class VideoDTOBuilder {
    private Long videoId;
    // 名称
    private String videoName;
    private String videoObjectId;
    // 主图
    private String videoImage;
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
    private String describe;

    private VideoDTOBuilder() {
    }

    public static VideoDTOBuilder aVideoDTO() {
        return new VideoDTOBuilder();
    }

    public VideoDTOBuilder videoId(Long videoId) {
        this.videoId = videoId;
        return this;
    }

    public VideoDTOBuilder videoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    public VideoDTOBuilder videoObjectId(String videoObjectId) {
        this.videoObjectId = videoObjectId;
        return this;
    }

    public VideoDTOBuilder videoImage(String videoImage) {
        this.videoImage = videoImage;
        return this;
    }

    public VideoDTOBuilder videoUpOfUser(Long videoUpOfUser) {
        this.videoUpOfUser = videoUpOfUser;
        return this;
    }

    public VideoDTOBuilder videoType(String videoType) {
        this.videoType = videoType;
        return this;
    }

    public VideoDTOBuilder playSum(Integer playSum) {
        this.playSum = playSum;
        return this;
    }

    public VideoDTOBuilder commentSum(Integer commentSum) {
        this.commentSum = commentSum;
        return this;
    }

    public VideoDTOBuilder collectSum(Integer collectSum) {
        this.collectSum = collectSum;
        return this;
    }

    public VideoDTOBuilder supportSum(Integer supportSum) {
        this.supportSum = supportSum;
        return this;
    }

    public VideoDTOBuilder opposeSum(Integer opposeSum) {
        this.opposeSum = opposeSum;
        return this;
    }

    public VideoDTOBuilder videoLength(String videoLength) {
        this.videoLength = videoLength;
        return this;
    }

    public VideoDTOBuilder describe(String describe) {
        this.describe = describe;
        return this;
    }

    public VideoDTO build() {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setVideoId(videoId);
        videoDTO.setVideoName(videoName);
        videoDTO.setVideoObjectId(videoObjectId);
        videoDTO.setVideoImage(videoImage);
        videoDTO.setVideoUpOfUser(videoUpOfUser);
        videoDTO.setVideoType(videoType);
        videoDTO.setPlaySum(playSum);
        videoDTO.setCommentSum(commentSum);
        videoDTO.setCollectSum(collectSum);
        videoDTO.setSupportSum(supportSum);
        videoDTO.setOpposeSum(opposeSum);
        videoDTO.setVideoLength(videoLength);
        videoDTO.setDescribe(describe);
        return videoDTO;
    }
}
