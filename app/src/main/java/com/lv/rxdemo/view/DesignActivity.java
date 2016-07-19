package com.lv.rxdemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lv.rxdemo.R;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.view.base.SwipeActivity;

public class DesignActivity extends SwipeActivity {

    private static final String EXTRA_DESIGN = "EXTRA_DESIGN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
    }

    public static Intent launchDetail(Context context, VRModel vrModel) {
        Intent intent = new Intent(context, DesignActivity.class);
        intent.putExtra(EXTRA_DESIGN, vrModel);
        return intent;
    }
}
