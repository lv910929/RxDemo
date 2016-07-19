package com.lv.rxdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lv on 2016/7/13.
 */
public class HomeDesign implements Serializable{
    /**
     * currentPageNo : 1
     * data : []
     * pageSize : 5
     * start : 0
     * totalCount : 3
     * totalPageCount : 1
     */

    private int currentPageNo;
    private int pageSize;
    private int start;
    private int totalCount;
    private int totalPageCount;
    private List<VRModel> data;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<VRModel> getData() {
        return data;
    }

    public void setData(List<VRModel> data) {
        this.data = data;
    }
}
