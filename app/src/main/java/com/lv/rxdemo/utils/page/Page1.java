package com.lv.rxdemo.utils.page;

/**
 * 分页策略1: pageIndex, pageSize
 * Created by Lv on 2016/10/10.
 */
public abstract class Page1 extends IPage {
    @Override
    public int handlePageIndex(int currPageIndex) {
        return ++currPageIndex;
    }

    @Override
    protected int handlePage(int currPageIndex, int pageSize) {
        return pageSize;
    }
}
