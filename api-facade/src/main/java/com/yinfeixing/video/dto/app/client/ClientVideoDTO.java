package com.yinfeixing.video.dto.app.client;

import com.yinfeixing.video.dto.video.VideoCommentDTO;
import com.yinfeixing.video.dto.video.VideoDTO;
import com.yinfeixing.video.dto.video.VideoDownloadDTO;

import java.util.List;

/**
 * 视频对象
 */
public class ClientVideoDTO extends VideoDTO {

    // 视频别名
    private List<String> videoAliasList;
    // 摘要、简介
    private String summary;
    // 影片分辨率
    private String videoResolution;
    // 地区
    private String zoneName;
    //语言
    private String languageName;
    // 上映时间及地区
    private String releaseTime;
    // 年代
    private String yearNum;
    // 主图
    private String videoImage;
    // 剧情描述
    private String describe;
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
    // 图片列表
    private List<String> videoImageList;
    // 下载信息列表
    private List<VideoDownloadDTO> videoDownloadList;
    private List<VideoCommentDTO> videoCommentList;

    public List<String> getVideoAliasList() {
        return videoAliasList;
    }

    public void setVideoAliasList(List<String> videoAliasList) {
        this.videoAliasList = videoAliasList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public List<VideoCommentDTO> getVideoCommentList() {
        return videoCommentList;
    }

    public void setVideoCommentList(List<VideoCommentDTO> videoCommentList) {
        this.videoCommentList = videoCommentList;
    }
}
