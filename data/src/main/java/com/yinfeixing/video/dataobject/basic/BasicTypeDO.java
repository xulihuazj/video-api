package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class BasicTypeDO extends DOBaseEntity {
    private Integer typeId;

    private String typeName;

    private String typeStatus;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus == null ? null : typeStatus.trim();
    }
}