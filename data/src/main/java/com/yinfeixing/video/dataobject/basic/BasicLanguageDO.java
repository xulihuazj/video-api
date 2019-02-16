package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class BasicLanguageDO extends DOBaseEntity {
    private Integer languageId;

    private String languageName;

    private String languageStatus;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName == null ? null : languageName.trim();
    }

    public String getLanguageStatus() {
        return languageStatus;
    }

    public void setLanguageStatus(String languageStatus) {
        this.languageStatus = languageStatus == null ? null : languageStatus.trim();
    }
}