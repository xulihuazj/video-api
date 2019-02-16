package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class BasicYearDO extends DOBaseEntity {
    private Integer yearId;

    private Integer yearNum;

    private String yearStatus;

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getYearNum() {
        return yearNum;
    }

    public void setYearNum(Integer yearNum) {
        this.yearNum = yearNum;
    }

    public String getYearStatus() {
        return yearStatus;
    }

    public void setYearStatus(String yearStatus) {
        this.yearStatus = yearStatus == null ? null : yearStatus.trim();
    }
}