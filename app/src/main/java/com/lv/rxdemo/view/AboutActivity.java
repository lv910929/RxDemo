package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityAboutBinding;
import com.lv.rxdemo.viewmodel.AboutViewModel;

public class AboutActivity extends AppCompatActivity{

    private ActivityAboutBinding activityAboutBinding;

    private AboutViewModel aboutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding = DataBindingUtil.setContentView(AboutActivity.this, R.layout.activity_about);
        aboutViewModel = new AboutViewModel(AboutActivity.this);
        activityAboutBinding.setAdoutViewModel(aboutViewModel);
        initToolBar("关于");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out);
    }
}
