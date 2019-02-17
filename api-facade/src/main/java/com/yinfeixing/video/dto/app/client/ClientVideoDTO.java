package com.yinfeixing.video.dto.app.client;

import com.yinfeixing.video.dto.video.VideoDTO;
import com.yinfeixing.video.dto.video.VideoDownloadDTO;
import com.yinfeixing.video.dto.video.VideoReleaseDTO;

import java.util.List;

/**
 * 视频对象
 */
public class ClientVideoDTO extends VideoDTO {

    // 年代
    private Integer videoYear;

    private List<VideoReleaseDTO> videoReleaseList;

    // 视频别名
    private List<String> videoAliasList;

    //导演
    private List<String> videoDirectorList;

    // 演员表
    private List<String> videoPerformerList;

    // 类型
    private List<String> videoTypeList;

    // 语言
    private List<String> videoLanguageList;

    // 地区
    private List<String> videoZoneList;

    // 摘要、简介
    private String summary;

    // 剧情描述
    private String describe;

    // 图片列表
    private List<String> videoImageList;

    // 下载信息列表
    private List<VideoDownloadDTO> videoDownloadList;


    public Integer getVideoYear() {
        return videoYear;
    }

    public void setVideoYear(Integer videoYear) {
        this.videoYear = videoYear;
    }

    public List<VideoReleaseDTO> getVideoReleaseList() {
        return videoReleaseList;
    }

    public void setVideoReleaseList(List<VideoReleaseDTO> videoReleaseList) {
        this.videoReleaseList = videoReleaseList;
    }

    public List<String> getVideoAliasList() {
        return videoAliasList;
    }

    public void setVideoAliasList(List<String> videoAliasList) {
        this.videoAliasList = videoAliasList;
    }

    public List<String> getVideoDirectorList() {
        return videoDirectorList;
    }

    public void setVideoDirectorList(List<String> videoDirectorList) {
        this.videoDirectorList = videoDirectorList;
    }

    public List<String> getVideoPerformerList() {
        return videoPerformerList;
    }

    public void setVideoPerformerList(List<String> videoPerformerList) {
        this.videoPerformerList = videoPerformerList;
    }

    public List<String> getVideoTypeList() {
        return videoTypeList;
    }

    public void setVideoTypeList(List<String> videoTypeList) {
        this.videoTypeList = videoTypeList;
    }

    public List<String> getVideoLanguageList() {
        return videoLanguageList;
    }

    public void setVideoLanguageList(List<String> videoLanguageList) {
        this.videoLanguageList = videoLanguageList;
    }

    public List<String> getVideoZoneList() {
        return videoZoneList;
    }

    public void setVideoZoneList(List<String> videoZoneList) {
        this.videoZoneList = videoZoneList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<String> getVideoImageList() {
        return videoImageList;
    }

    public void setVideoImageList(List<String> videoImageList) {
        this.videoImageList = videoImageList;
    }

    public List<VideoDownloadDTO> getVideoDownloadList() {
        return videoDownloadList;
    }

    public void setVideoDownloadList(List<VideoDownloadDTO> videoDownloadList) {
        this.videoDownloadList = videoDownloadList;
    }
}
