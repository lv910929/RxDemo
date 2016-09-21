package com.lv.rxdemo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.lv.rxdemo.R;
import com.lv.rxdemo.utils.AppUtils;

/**
 * Created by Lv on 2016/9/20.
 */
public class GuideActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int color = getResources().getColor(R.color.colorPrimary);
        addSlide(AppIntroFragment.newInstance("NO.1", "第一个引导页", R.drawable.guide_one, color));
        addSlide(AppIntroFragment.newInstance("NO.2", "第二个引导页", R.drawable.guide_two, color));
        addSlide(AppIntroFragment.newInstance("NO.3", "第三个引导页", R.drawable.guide_three, color));
        setBarColor(color);
        setDoneText("立即开启");
        setSkipText("跳过");
        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        AppUtils.setFirstRun(false);
        AppUtils.checkLogin(GuideActivity.this);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        AppUtils.setFirstRun(false);
        AppUtils.checkLogin(GuideActivity.this);
    }
}
