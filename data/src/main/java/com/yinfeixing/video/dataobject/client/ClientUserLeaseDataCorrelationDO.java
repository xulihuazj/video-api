package com.yinfeixing.video.dataobject.client;

import com.cf.entity.DOBaseEntity;
import java.util.Date;

public class ClientUserLeaseDataCorrelationDO extends DOBaseEntity {
    private Integer userLeaseDataCorrelationId;

    private Long userId;

    private Long leaseId;

    private Date startTime;

    private Date endTime;

    private Long subjectId;

    private Long renterId;

    private String status;

    private Integer projectId;

    public Integer getUserLeaseDataCorrelationId() {
        return userLeaseDataCorrelationId;
    }

    public void setUserLeaseDataCorrelationId(Integer userLeaseDataCorrelationId) {
        this.userLeaseDataCorrelationId = userLeaseDataCorrelationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}