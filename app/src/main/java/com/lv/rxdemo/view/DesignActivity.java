package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityDesignBinding;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.view.base.SwipeBackActivity;
import com.lv.rxdemo.viewmodel.DesignViewModel;

public class DesignActivity extends SwipeBackActivity {

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
        initUI();
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = this.getIntent().getExtras();
        vrModel = (VRModel) bundle.getSerializable(EXTRA_DESIGN);
    }

    //初始化DataBinding
    protected void initDataBinding() {
        activityDesignBinding = DataBindingUtil.setContentView(DesignActivity.this, R.layout.activity_design);
        designViewModel = new DesignViewModel(DesignActivity.this, vrModel);
        activityDesignBinding.setDesignViewModel(designViewModel);
        setStatusTransparent();
    }

    @Override
    protected void initUI() {
        super.initUI();
        setToolbar(activityDesignBinding.toolbar, designViewModel.getModelHomeApartmentDesignName());
        setFloatingActionMenu();
    }

    private void setFloatingActionMenu() {
        /*activityDesignBinding.floatBtnDesign.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

}
