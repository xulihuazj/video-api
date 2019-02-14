package com.yinfeiixng.video.model;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {

    private static final long serialVersionUID = -6001577172423282094L;

    /*默认页码*/
    private static final int DEFAULT_PAGENO = 1;
    /*默认页容量*/
    private static final int DEFAULT_PAGESIZE = 20;

    // 当前页码
    private int pageNo;
    // 此页容量
    private int pageSize;
    // 总条数
    private int totalCount;
    // 总页数
    private int totalPage;
    // 数据集合
    private List<T> result;

    /**
     * 无参构造器，默认pageNo=1，pageSize=20
     */
    public PageModel() {
        this(DEFAULT_PAGENO, DEFAULT_PAGESIZE);
    }

    public PageModel(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0, null);
    }

    public PageModel(int pageNo, int pageSize, int totalCount, List<T> result) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        this.setResult(result);
    }

    /**
     * 是否时第一页
     *
     * @return
     */
    public boolean isFirstPage() {
        return this.pageNo <= 1;
    }

    /**
     * 是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return this.pageNo >= this.getTotalPage();
    }

    /**
     * 获取上一页的页码
     *
     * @return
     */
    public int getPrevPage() {
        if (this.pageNo <= 1) {
            return 1;
        }
        return this.pageNo - 1;
    }

    /**
     * 获取下一页的页码
     *
     * @return
     */
    public int getNextPage() {
        if (this.isLastPage()) {
            return this.getTotalPage();
        }
        return this.pageNo + 1;
    }

    /**
     * 获取第一条记录位置，用于数据库查询时设置的第一条纪录
     *
     * @return
     */
    public int getFirstResult() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo <= 0 ? DEFAULT_PAGENO : pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGESIZE : pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount <= 0 ? 0 : totalCount;
    }

    public int getTotalPage() {
        return (getTotalCount() - 1) / getPageSize() + 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage < 0 ? 0 : totalPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


}
