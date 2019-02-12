package com.yinfeixing.entity;

import java.util.Date;

public class BaseEntity extends ToString {
    public Date createTime;
    private Date lastModifyTime;

    public BaseEntity() {
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
