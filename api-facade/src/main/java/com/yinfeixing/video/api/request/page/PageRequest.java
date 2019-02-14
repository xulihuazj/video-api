package com.yinfeixing.video.api.request.page;

import com.yinfeixing.entity.ToString;

import javax.validation.constraints.NotNull;

/**
 * 分页请求参数
 * PageRequest.java 1.0.0 2018/3/6  14:40
 */
public class PageRequest extends ToString {

    private static final long serialVersionUID = 5088746450029351994L;

    //当前页
    @NotNull(message = "当前页不能为空")
    private Integer pageNum;

    //每页条数:每页最大100条
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize > 100 ? 100 : pageSize;
    }

}
