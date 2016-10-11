package com.lv.rxdemo.utils.page;

/**
 * 分页策略2: startIndex, endIndex
 * Created by Lv on 2016/10/10.
 */
public abstract class Page2 extends IPage {
    @Override
    public int handlePageIndex(int currPageIndex) {
        return currPageIndex + pageSize;
    }

    @Override
    protected int handlePage(int currPageIndex, int pageSize) {
        return currPageIndex + pageSize - 1;
    }

    /**
     * 起始下标递减
     */
    public void decreaseStartIndex() {
        currPageIndex--;
        checkBound();
    }

    /**
     * 起始下标递减
     */
    public void decreaseStartIndex(int size) {
        currPageIndex -= size;
        checkBound();
    }

    /**
     * 边界检测
     */
    private void checkBound() {
        if (currPageIndex < getStartPageIndex() - pageSize) {
            currPageIndex = getStartPageIndex() - pageSize;
        }
    }
}
