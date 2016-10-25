package com.lv.rxdemo.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.lv.rxdemo.R;
import com.lv.rxdemo.utils.ActivityUtil;
import com.lv.rxdemo.utils.AppUtils;
import com.lv.rxdemo.view.MainActivity;

/**
 * Created by Lv on 2016/10/25.
 */

public class LoginViewModel implements LoginViewModelContact.ViewModel {

    private Context context;

    private LoginViewModelContact.LoginView loginView;

    public LoginViewModel(Context context, LoginViewModelContact.LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.float_button_register:
                loginView.redirectRegister();
                break;
            case R.id.button_login:
                if (loginView.validateLogin()) {
                    AppUtils.setHasLogin(true);
                    ActivityUtil.startActivityWithFinish((Activity) context, MainActivity.class, null);
                }
                break;
        }
    }

    @Override
    public void destroy() {
        reset();
    }

    private void reset() {
        context = null;
        loginView = null;
    }
}
