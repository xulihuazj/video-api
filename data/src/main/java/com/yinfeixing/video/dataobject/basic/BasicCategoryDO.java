package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class BasicCategoryDO extends DOBaseEntity {
    private Integer categoryId;

    private String categoryName;

    private String categoryStatus;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus == null ? null : categoryStatus.trim();
    }
}