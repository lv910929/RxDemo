package com.lv.rxdemo.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

        initDataBinding();
        activityMainBinding.toolbar.setTitle("");
        setSupportActionBar(activityMainBinding.toolbar);
        setupRecyclerMain(activityMainBinding.recyclerMain);
    }

    //初始化DataBinding
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
    }
}
