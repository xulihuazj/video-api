package com.yinfeixing.video.dto;

import com.yinfeixing.entity.ToString;

import java.util.Date;

public class BaseDTO extends ToString {

    private static final long serialVersionUID = -4337725118362510880L;

    private Date createTime;

    private Date lastModifyTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }


}
