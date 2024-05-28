package io.github.lcn29.web.starter.model;

import java.io.Serializable;

/**
 * 分页数据
 *
 * @author lcn29
 * @date 2024-05-05 17:52:29
 */
public class PageData<T> implements Serializable {

    private int pageNum;

    private int pageSize;

    private long total;

    private T data;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
