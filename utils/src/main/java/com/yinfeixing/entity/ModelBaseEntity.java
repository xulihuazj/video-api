package com.yinfeixing.entity;

import java.util.Map;

public class ModelBaseEntity extends BaseEntity {
    private Map<String, String> extInfo;

    public Map<String, String> getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
