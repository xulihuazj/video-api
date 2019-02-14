package com.yinfeixing.video.api.response.page;

import com.yinfeixing.entity.ToString;

public class PageResponse extends ToString {

    private static final long serialVersionUID = -7977504122202614680L;

    /**
     *  总条数
     */
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
