package com.yinfeixing.video.response.page;

import com.yinfeixing.entity.ToString;

public class PageResponse extends ToString {

    private static final long serialVersionUID = -7977504122202614680L;

    /**
     * 总条数
     */
    private int total;

    // 总页数
    private int totalPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
