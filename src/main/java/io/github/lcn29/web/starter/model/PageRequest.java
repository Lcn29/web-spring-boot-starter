package io.github.lcn29.web.starter.model;

import java.io.Serializable;

/**
 * 分页请求
 *
 * @author lcn29
 * @date 2024-05-05 17:50:36
 */
public class PageRequest implements Serializable {

    private int pageNum;

    private int pageSize;

    private boolean countTotal;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isCountTotal() {
        return countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }
}
