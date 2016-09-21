package com.lv.rxdemo.app;

import android.app.Application;

/**
 * Created by Lv on 2016/7/18.
 */
public class RxApplication extends Application {

    private static RxApplication INSTANCE;

    public static RxApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
