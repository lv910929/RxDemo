package com.lv.rxdemo.app;

import android.app.Application;

import com.lv.rxdemo.utils.AppUtils;

/**
 * Created by Lv on 2016/7/18.
 */
public class RxApplication extends Application {

    private static RxApplication INSTANCE;

    public static RxApplication getInstance() {
        return INSTANCE;
    }

    public static boolean isFirstInstall;//第一次安装

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        isFirstInstall = AppUtils.isFirstRun();
    }
}
