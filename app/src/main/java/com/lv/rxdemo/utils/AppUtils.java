package com.lv.rxdemo.utils;

import android.app.Activity;
import android.content.Intent;

import com.lv.rxdemo.R;
import com.lv.rxdemo.app.RxApplication;
import com.lv.rxdemo.view.LoginActivity;
import com.lv.rxdemo.view.MainActivity;

/**
 * Time:8:42
 */
public class AppUtils {

    //判断有没有登录
    public static void checkLogin(Activity activity) {
        if (AppUtils.hasLogin()) {//说明已经登录
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
            activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
        } else {
            activity.startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
            activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
        }
    }

    public static boolean isFirstRun() {
        return PrefUtils.getBoolean(RxApplication.getInstance(), "isFirstRun", true);
    }

    public static void setFirstRun(boolean isFirstRun) {
        PrefUtils.putBoolean(RxApplication.getInstance(), "isFirstRun", isFirstRun);
    }

    public static boolean hasLogin() {
        return PrefUtils.getBoolean(RxApplication.getInstance(), "hasLogin", false);
    }

    public static void setHasLogin(boolean hasLogin) {
        PrefUtils.putBoolean(RxApplication.getInstance(), "hasLogin", hasLogin);
    }

    public static boolean shakePicture() {
        return PrefUtils.getBoolean(RxApplication.getInstance(), "shakePicture", true);
    }

    public static void setShakePicture(boolean isEnable) {
        PrefUtils.putBoolean(RxApplication.getInstance(), "shakePicture", isEnable);
    }

}
