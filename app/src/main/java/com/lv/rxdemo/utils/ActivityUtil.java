package com.lv.rxdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.lv.rxdemo.R;

import java.util.Map;

public class ActivityUtil {

    public static void startActivityNotInActivity(Context context, Class targetActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivity(Activity activity, Class targetActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivityWithFinish(Activity activity, Class targetActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivity(Activity activity, Class targetActivity, Map<String, String> map, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, targetActivity);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivity(Activity activity, Uri uri, Bundle bundle) {
        if (!DoubleClickUtil.isFastDoubleClick())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivity(Activity activity, String action, Bundle bundle) {
        if (!DoubleClickUtil.isFastDoubleClick())
            return;
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivityForResult(Activity activity, String action, Bundle bundle, int result) {
        if (!DoubleClickUtil.isFastDoubleClick())
            return;
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, result);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivityForResult(Activity activity, Class targetActivity, int result) {
        startActivityForResult(activity, targetActivity, result, null);
    }


    public static void startActivityForResult(Activity activity, String action, Uri uri, int result) {
        startActivityForResult(activity, action, uri, result, null);
    }

    public static void startActivityForResult(Activity activity, String action, Uri uri, int result, Bundle bundle) {
        if (!!DoubleClickUtil.isFastDoubleClick())
            return;
        Intent intent = new Intent(action, uri);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, result);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivityForResult(Activity activity, Class targetActivity, int result, Bundle bundle) {
        if (!!DoubleClickUtil.isFastDoubleClick())
            return;
        Intent intent = new Intent();
        intent.setClass(activity, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, result);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int result, Bundle bundle) {
        if (intent == null || !!DoubleClickUtil.isFastDoubleClick())
            return;
        if (bundle != null)
            intent.putExtras(bundle);
        activity.startActivityForResult(intent, result);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }
}
