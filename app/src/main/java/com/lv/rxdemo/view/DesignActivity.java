package com.lv.rxdemo.view;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityDesignBinding;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.viewmodel.DesignViewModel;

public class DesignActivity extends BaseActivity {

    public static final String EXTRA_DESIGN = "EXTRA_DESIGN";

    private VRModel vrModel;

    private ActivityDesignBinding activityDesignBinding;

    private DesignViewModel designViewModel;

    private boolean isFabOpen = false;//判断fab菜单是否打开

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTheme();
        initDataBinding();
        initToolBar(designViewModel.getModelHomeApartmentDesignName());
        setFloatingActionMenu();
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = this.getIntent().getExtras();
        vrModel = (VRModel) bundle.getSerializable(EXTRA_DESIGN);
    }

    //初始化DataBinding
    private void initDataBinding() {
        activityDesignBinding = DataBindingUtil.setContentView(DesignActivity.this, R.layout.activity_design);
        designViewModel = new DesignViewModel(DesignActivity.this, vrModel);
        activityDesignBinding.setDesignViewModel(designViewModel);
    }

    //设置toolbar
    private void initToolBar(String title) {
        setTitle(title);
        setSupportActionBar(activityDesignBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityDesignBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFloatingActionMenu() {
        activityDesignBinding.floatBtnDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFabOpen) {//说明已经打开
                    closeMenu();
                } else {
                    openMenu();
                }
            }
        });
        activityDesignBinding.textEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
    }

    //打开菜单
    private void openMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(activityDesignBinding.floatBtnDesign, "rotation", 0, -155, -135);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        activityDesignBinding.textEmpty.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        activityDesignBinding.textEmpty.startAnimation(alphaAnimation);
        isFabOpen = true;
    }

    //关闭菜单
    private void closeMenu() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(activityDesignBinding.floatBtnDesign, "rotation", -135, 20, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        activityDesignBinding.textEmpty.startAnimation(alphaAnimation);
        activityDesignBinding.textEmpty.setVisibility(View.GONE);
        isFabOpen = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out);
    }
}
