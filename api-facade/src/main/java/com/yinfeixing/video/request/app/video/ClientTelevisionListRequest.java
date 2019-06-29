package com.yinfeixing.video.request.app.video;

import com.yinfeixing.video.request.page.PageRequest;

/**
 * 电视剧列表
 */
public class ClientTelevisionListRequest extends PageRequest {

    /**
     * 排序类型
     */
    private String sortType;

    // 地区类型（大陆剧、港台剧、日韩剧、欧美剧）
    private Long zoneType;

    // 年代
    private Long yearId;

    // 类型
    private Long typeId;

    // 搜索内容
    private String searchContent;

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Long getZoneType() {
        return zoneType;
    }

    public void setZoneType(Long zoneType) {
        this.zoneType = zoneType;
    }

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

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
