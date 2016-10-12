package com.lv.rxdemo.config;

import android.os.Environment;

/**
 * Created by Lv on 2016/10/11.
 */

public class RxConfig {

    public static final String SDCARD_ROOT_PATH = Environment.getExternalStorageDirectory().getPath();

    public static final String SAVE_PATH_IN_SDCARD = "/rxdemo/download/";

    public static final String SAVE_IMAGE_PATH = "/rxdemo/Images/";

    public static final String PLATFORM_TYPE = "Android";

}
