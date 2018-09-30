package com.tangshengbo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/9/30
 */
public class PageBean<T> implements Serializable {

    //总记录数
    private long total;
    //当前页记录
    private List<T> rows;
    //当前页
    private int pageNum;
    //页大小
    private int size;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PageBean(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageBean(long total, List<T> rows, int pageNum, int size) {
        this.total = total;
        this.rows = rows;
        this.pageNum = pageNum;
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
