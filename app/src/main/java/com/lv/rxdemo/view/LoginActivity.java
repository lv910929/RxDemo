package com.lv.rxdemo.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityLoginBinding;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.viewmodel.LoginViewModel;
import com.lv.rxdemo.viewmodel.LoginViewModelContact;

public class LoginActivity extends BaseActivity implements LoginViewModelContact.LoginView {

    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;
    private LoginViewModelContact.LoginView loginView = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initUI();
    }

    @Override
    protected void initDataBinding() {
        activityLoginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(LoginActivity.this, loginView);
        activityLoginBinding.setLoginViewModel(loginViewModel);
    }

    @Override
    protected void initUI() {
        super.initUI();
        initToolBar("登录", false);
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

    @Override
    public void redirectRegister() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    activityLoginBinding.floatButtonRegister,
                    activityLoginBinding.floatButtonRegister.getTransitionName());
            startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    //验证登录表单
    public boolean validateLogin() {
        boolean flag = true;
        if (!activityLoginBinding.validateTextPassword.validate()) flag = false;
        if (!activityLoginBinding.validateTextPassword.validate()) flag = false;
        return flag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel.destroy();
    }
}
