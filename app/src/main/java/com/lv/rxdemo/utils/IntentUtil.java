package com.lv.rxdemo.utils;

import android.content.Context;
import android.view.Gravity;

import com.lv.rxdemo.R;
import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by Lv on 2016/7/20.
 */
public class IntentUtil {

    public static void redirectFinestWebView(Context context, String url) {
        new FinestWebView.Builder(context)
                .theme(R.style.FinestWebViewTheme)
                .toolbarScrollFlags(0)
                .statusBarColorRes(R.color.colorPrimaryDark)
                .toolbarColorRes(R.color.colorPrimary)
                .titleColorRes(R.color.finestWhite)
                .iconDefaultColorRes(R.color.finestWhite)
                .progressBarColorRes(R.color.finestWhite)
                .swipeRefreshColorRes(R.color.colorPrimary)
                .menuSelector(R.drawable.selector_light_theme)
                .menuTextGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT)
                .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                .dividerHeight(0)
                .gradientDivider(false)
                .setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold, R.anim.slide_right_out)
                .disableIconBack(true)
                .disableIconForward(true)
                .show(url);
    }
}
