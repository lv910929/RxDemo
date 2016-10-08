package com.lv.rxdemo.model;

import java.io.Serializable;

/**
 * Created by Lv on 2016/6/30.
 */
public class MessageEvent implements Serializable {

    private int what;
    private Object obj;

    public MessageEvent(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
