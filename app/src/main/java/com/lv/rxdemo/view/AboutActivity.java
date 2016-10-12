package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityAboutBinding;
import com.lv.rxdemo.view.base.SwipeBackActivity;
import com.lv.rxdemo.viewmodel.AboutViewModel;

public class AboutActivity extends SwipeBackActivity {

    private ActivityAboutBinding activityAboutBinding;

    private AboutViewModel aboutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTheme();
        initDataBinding();
        initUI();
    }

    //初始化DataBinding
    protected void initDataBinding() {
        activityAboutBinding = DataBindingUtil.setContentView(AboutActivity.this, R.layout.activity_about);
        aboutViewModel = new AboutViewModel(AboutActivity.this);
        activityAboutBinding.setAdoutViewModel(aboutViewModel);
        setStatusTransparent();
    }

    @Override
    protected void initUI() {
        super.initUI();
        setToolbar(activityAboutBinding.toolbar, "关于");
        setFloatingMenu();
    }

    //设置FloatingActionMenu
    private void setFloatingMenu() {
        activityAboutBinding.menuLabelsRight.setClosedOnTouchOutside(true);
    }

    @Override
    public void onBackPressed() {
        if (activityAboutBinding.menuLabelsRight.isOpened()) {//说明是打开的
            activityAboutBinding.menuLabelsRight.close(true);
        } else {
            super.onBackPressed();
        }
    }
}
