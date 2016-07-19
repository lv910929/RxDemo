package com.lv.rxdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lv.rxdemo.data.DesignFactory;
import com.lv.rxdemo.model.ModelDesignData;
import com.lv.rxdemo.model.VRModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lv on 2016/7/18.
 */
public class MainViewModel implements MainViewModelContact.ViewModel{

    private static final String TAG = "MainActivity";

    public ObservableInt dataProgress;
    public ObservableInt designList;

    private Context mContext;
    private Subscription mSubscription;
    private MainViewModelContact.MainView mainView;

    public MainViewModel(@NonNull MainViewModelContact.MainView mainView, @NonNull Context mContext) {
        this.mainView = mainView;
        this.mContext = mContext;
        dataProgress = new ObservableInt(View.GONE);
        designList = new ObservableInt(View.VISIBLE);

        initializeViews();
        getData();
    }

    public void initializeViews() {
        designList.set(View.GONE);
        dataProgress.set(View.VISIBLE);
    }

    public void getData() {
        mSubscription = DesignFactory.getService()
                .getModelDesignData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ModelDesignData>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        dataProgress.set(View.GONE);
                        designList.set(View.VISIBLE);
                        Toast.makeText(mContext, "服务器异常！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ModelDesignData modelDesignData) {
                        dataProgress.set(View.GONE);
                        designList.set(View.VISIBLE);
                        if (modelDesignData != null && modelDesignData.getModelHomeDesigns() != null) {
                            List<VRModel> vrModels = modelDesignData.getModelHomeDesigns().getData();
                            if (vrModels == null) {
                                vrModels = new ArrayList<VRModel>();
                            }
                            if (mainView != null) {
                                mainView.loadData(vrModels);
                            }
                        }
                    }
                });
    }

    @Override
    public void destroy() {
        reset();
    }

    private void unSubscribeFromObservable() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void reset() {
        unSubscribeFromObservable();
        mSubscription = null;
        mContext = null;
        mainView = null;
    }
}
