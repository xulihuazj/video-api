package com.yinfeixing.video.dataobject.client;

import com.cf.entity.DOBaseEntity;

public class ClientUserRelatedInfoDO extends DOBaseEntity {
    private Long id;

    private Long userId;

    private Long relatedId;

    private String relatedType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public String getRelatedType() {
        return relatedType;
    }

    public void setRelatedType(String relatedType) {
        this.relatedType = relatedType == null ? null : relatedType.trim();
    }

}