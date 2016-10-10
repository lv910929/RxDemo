package com.lv.rxdemo.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.lv.rxdemo.R;
import com.lv.rxdemo.config.Constant;
import com.lv.rxdemo.model.MessageEvent;
import com.lv.rxdemo.utils.AppUtils;
import com.lv.rxdemo.utils.IntentUtil;
import com.lv.rxdemo.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Lv on 2016/7/20.
 */
public class AboutViewModel {

    private Context context;

    public AboutViewModel(Context context) {
        this.context = context;
    }

    private String githubAddress;
    private String emailAddress;

    public String getGithubAddress() {
        return "github地址";
    }

    public String getEmailAddress() {
        return "1025202464@qq.com";
    }

    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.item_github_address:
                IntentUtil.redirectFinestWebView(context, Constant.GITHUB_URL);
                break;
            case R.id.btn_exit_login:
                AppUtils.setHasLogin(false);
                EventBus.getDefault().post(new MessageEvent(1, null));
                context.startActivity(new Intent(context, LoginActivity.class));
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
                break;
        }
    }
}
