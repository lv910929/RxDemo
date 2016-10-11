package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.lv.rxdemo.R;
import com.lv.rxdemo.config.Constant;
import com.lv.rxdemo.databinding.ActivityAboutBinding;
import com.lv.rxdemo.utils.IntentUtil;
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
        initToolBar("关于");
        setFloatingMenu();
    }

    //初始化DataBinding
    protected void initDataBinding() {
        activityAboutBinding = DataBindingUtil.setContentView(AboutActivity.this, R.layout.activity_about);
        aboutViewModel = new AboutViewModel(AboutActivity.this);
        activityAboutBinding.setAdoutViewModel(aboutViewModel);
        setStatusTransparent();
    }

    //设置toolbar
    private void initToolBar(String title) {
        setTitle(title);
        setSupportActionBar(activityAboutBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityAboutBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //设置FloatingActionMenu
    private void setFloatingMenu() {
        activityAboutBinding.menuLabelsRight.setClosedOnTouchOutside(true);
        activityAboutBinding.buttonPraise.setOnClickListener(clickListener);
        activityAboutBinding.buttonStar.setOnClickListener(clickListener);
        activityAboutBinding.buttonComment.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_praise:
                    IntentUtil.redirectFinestWebView(AboutActivity.this, Constant.GITHUB_URL);
                    break;
                case R.id.button_star:
                    IntentUtil.redirectFinestWebView(AboutActivity.this, Constant.MY_GITHUB_URL);
                    break;
                case R.id.button_comment:
                    IntentUtil.redirectFinestWebView(AboutActivity.this, Constant.GITHUB_URL);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (activityAboutBinding.menuLabelsRight.isOpened()) {//说明是打开的
            activityAboutBinding.menuLabelsRight.close(true);
        } else {
            super.onBackPressed();
        }
    }
}
