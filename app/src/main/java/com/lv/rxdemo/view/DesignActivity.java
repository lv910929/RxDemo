package com.lv.rxdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityDesignBinding;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.viewmodel.DesignViewModel;
import com.lv.rxdemo.viewmodel.DesignViewModelContact;

public class DesignActivity extends AppCompatActivity implements DesignViewModelContact.DesignView {

    public static final String EXTRA_DESIGN = "EXTRA_DESIGN";

    private VRModel vrModel;

    private ActivityDesignBinding activityDesignBinding;

    private DesignViewModel designViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        activityDesignBinding = DataBindingUtil.setContentView(DesignActivity.this, R.layout.activity_design);
        designViewModel = new DesignViewModel(vrModel);
        activityDesignBinding.setDesignViewModel(designViewModel);
        initToolBar(designViewModel.getModelHomeApartmentDesignName());
    }

    private void initData() {
        Bundle bundle = this.getIntent().getExtras();
        vrModel = (VRModel) bundle.getSerializable(EXTRA_DESIGN);
    }

    protected void initToolBar(String title) {
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
    public Context getContext() {
        return DesignActivity.this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out);
    }
}
