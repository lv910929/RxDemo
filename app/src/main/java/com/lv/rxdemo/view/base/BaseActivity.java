package com.lv.rxdemo.view.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lv.rxdemo.R;
import com.lv.rxdemo.utils.HideInputUtils;

/**
 * Created by Lv on 2016/9/20.
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextView titleText;

    protected void initUI() {

    }

    //设置通用的toolbar
    protected void initToolBar(String title,boolean needBack) {
        toolbar = (Toolbar) findViewById(R.id.toolbar_comm);
        titleText = (TextView) findViewById(R.id.text_title);
        setTitle("");
        titleText.setText(title);
        if (needBack){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    //隐藏软键盘
    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showProgress(ProgressBar progressBar, View contentView) {
        progressBar.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    protected void hideProgress(ProgressBar progressBar, View contentView) {
        progressBar.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    protected void showView(View showView, View hideView) {
        showView.setVisibility(View.VISIBLE);
        hideView.setVisibility(View.GONE);
    }

    protected void hideView(View showView, View hideView) {
        showView.setVisibility(View.GONE);
        hideView.setVisibility(View.VISIBLE);
    }

    /**
     * 点击屏幕空白处，隐藏输入法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (HideInputUtils.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out);
    }
}
