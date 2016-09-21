package com.lv.rxdemo.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import com.lv.rxdemo.R;
import com.lv.rxdemo.utils.AppUtils;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.widget.ValidatedTextInputLayout;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ValidatedTextInputLayout validateTextPhone;
    private ValidatedTextInputLayout validateTextPassword;

    private FloatingActionButton floatButtonRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    @Override
    protected void initUI() {
        super.initUI();
        initToolBar("登录", false);

        validateTextPhone = (ValidatedTextInputLayout) findViewById(R.id.validate_text_phone);
        validateTextPassword = (ValidatedTextInputLayout) findViewById(R.id.validate_text_password);

        floatButtonRegister = (FloatingActionButton) findViewById(R.id.float_button_register);
        buttonLogin = (Button) findViewById(R.id.button_login);

        floatButtonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_button_register:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, floatButtonRegister, floatButtonRegister.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.button_login:
                if (validateLogin()) {
                    AppUtils.setHasLogin(true);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
                }
                break;
        }
    }

    private boolean validateLogin() {
        boolean flag = true;
        if (!validateTextPhone.validate()) flag = false;
        if (!validateTextPassword.validate()) flag = false;
        return flag;
    }
}
