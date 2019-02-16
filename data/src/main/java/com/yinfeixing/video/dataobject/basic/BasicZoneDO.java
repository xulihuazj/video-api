package com.yinfeixing.video.dataobject.basic;

import com.yinfeixing.entity.DOBaseEntity;

public class BasicZoneDO extends DOBaseEntity {
    private Integer zoneId;

    private String zoneName;

    private String zoneStatus;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(String zoneStatus) {
        this.zoneStatus = zoneStatus == null ? null : zoneStatus.trim();
    }
}