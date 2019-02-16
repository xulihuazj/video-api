package com.yinfeixing.video.request.app.video;

import com.yinfeixing.video.request.page.PageRequest;

public class ClientVideoListRequest extends PageRequest {

    // 年代
    private Long yearId;

    // 类型
    private Long typeId;

    //  语言
    private Long languageId;

    // 地区
    private Long zoneId;

    // 搜索内容
    private String searchContent;

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
