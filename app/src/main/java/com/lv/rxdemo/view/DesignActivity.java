package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTheme();
        initDataBinding();
        initToolBar(designViewModel.getModelHomeApartmentDesignName());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out);
    }
}
