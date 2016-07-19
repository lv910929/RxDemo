package com.lv.rxdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityMainBinding;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.viewmodel.MainViewModel;
import com.lv.rxdemo.viewmodel.MainViewModelContact;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainViewModelContact.MainView {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private MainViewModelContact.MainView mainView = this;

    private DesignAdapter designAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBinding();
        setupRecyclerMain(activityMainBinding.recyclerMain);
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainViewModel = new MainViewModel(mainView, getContext());
        activityMainBinding.setMainViewModel(mainViewModel);
    }

    private void setupRecyclerMain(RecyclerView recyclerMain) {
        designAdapter = new DesignAdapter();
        recyclerMain.setAdapter(designAdapter);
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void loadData(List<VRModel> vrModels) {
        designAdapter.setVrModels(vrModels);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
    }
}
