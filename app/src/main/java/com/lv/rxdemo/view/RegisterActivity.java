package com.lv.rxdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.lv.rxdemo.R;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.widget.ValidatedTextInputLayout;
import com.lv.rxdemo.widget.validator.DependencyValidator;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private CardView cardViewRegister;
    private FloatingActionButton floatButtonExit;

    private ValidatedTextInputLayout validateTextPhone;
    private ValidatedTextInputLayout validateTextPassword;
    private ValidatedTextInputLayout validateTextConfirm;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
    }

    @Override
    protected void initUI() {
        super.initUI();
        initToolBar("注册", false);
        cardViewRegister = (CardView) findViewById(R.id.card_view_register);
        floatButtonExit = (FloatingActionButton) findViewById(R.id.float_button_exit);

        validateTextPhone = (ValidatedTextInputLayout) findViewById(R.id.validate_text_phone);
        validateTextPassword = (ValidatedTextInputLayout) findViewById(R.id.validate_text_password);
        validateTextConfirm = (ValidatedTextInputLayout) findViewById(R.id.validate_text_confirm);
        buttonRegister = (Button) findViewById(R.id.button_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        validateTextConfirm.addValidator(new DependencyValidator(validateTextPassword, DependencyValidator.TYPE_EQUAL, getString(R.string.confirm_password_error)));
        floatButtonExit.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_button_exit:
                animateRevealClose();
                break;
            case R.id.button_register:
                if (validateRegister()) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    animateRevealClose();
                }
                break;
        }
    }


    private boolean validateRegister() {
        boolean flag = true;
        if (!validateTextPhone.validate()) flag = false;
        if (!validateTextPassword.validate()) flag = false;
        if (!validateTextConfirm.validate()) flag = false;
        return flag;
    }

    //进入动画
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cardViewRegister.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cardViewRegister, cardViewRegister.getWidth() / 2, 0, floatButtonExit.getWidth() / 2, cardViewRegister.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cardViewRegister.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    //退出动画
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cardViewRegister, cardViewRegister.getWidth() / 2, 0, cardViewRegister.getHeight(), floatButtonExit.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cardViewRegister.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                floatButtonExit.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        animateRevealClose();
    }
}
