package com.yinfeiixng.video.model;

import com.yinfeixing.entity.ToString;

import java.util.List;


public class PageResultModel<T> extends ToString {
    /**
     * 当前页数(需要手动set)
     */
    private int page;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 分页数据
     */
    private List<T> datas;

    public PageResultModel() {
    }

    public PageResultModel(List<T> datas, long total) {
        this.datas = datas;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
