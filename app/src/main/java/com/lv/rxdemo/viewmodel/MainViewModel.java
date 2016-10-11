package com.lv.rxdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lv.rxdemo.R;
import com.lv.rxdemo.config.Constant;
import com.lv.rxdemo.data.DesignFactory;
import com.lv.rxdemo.model.ModelDesignData;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.utils.IntentUtil;
import com.lv.rxdemo.utils.page.IPage;
import com.lv.rxdemo.utils.page.Page1;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lv on 2016/7/18.
 */
public class MainViewModel implements MainViewModelContact.ViewModel {

    private static final String TAG = "MainActivity";

    public ObservableInt dataProgress;
    public ObservableInt designList;
    public ObservableInt designButton;

    private Context mContext;
    private Subscription mSubscription;
    private MainViewModelContact.MainView mainView;

    private IPage page;

    public MainViewModel(@NonNull MainViewModelContact.MainView mainView, @NonNull Context mContext) {
        this.mainView = mainView;
        this.mContext = mContext;
        dataProgress = new ObservableInt(View.GONE);
        designList = new ObservableInt(View.VISIBLE);
        designButton = new ObservableInt(View.VISIBLE);

        initializeViews();
        initPage();
        getData();
    }

    public void initializeViews() {
        designList.set(View.GONE);
        designButton.set(View.GONE);
        dataProgress.set(View.VISIBLE);
    }

    //初始化分页
    private void initPage() {
        // pageIndex, pageSize策略
        page = new Page1() {
            @Override
            public void load(int param1, int param2) {
                mSubscription = DesignFactory.getService()
                        .getModelDesignData(1, param1)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
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
                                designButton.set(View.VISIBLE);
                                Toast.makeText(mContext, "服务器异常！", Toast.LENGTH_SHORT).show();
                                // 一定要调用，加载失败
                                page.finishLoad(false);
                            }

                            @Override
                            public void onNext(ModelDesignData modelDesignData) {

                                dataProgress.set(View.GONE);
                                designList.set(View.VISIBLE);
                                designButton.set(View.VISIBLE);
                                if (modelDesignData != null && modelDesignData.getModelHomeDesigns() != null) {
                                    List<VRModel> vrModels = modelDesignData.getModelHomeDesigns().getData();
                                    if (vrModels == null) {
                                        vrModels = new ArrayList<VRModel>();
                                    }
                                    if (mainView != null) {
                                        mainView.loadData(vrModels);
                                    }
                                    // 一定要调用，加载成功
                                    page.finishLoad(true);
                                } else {
                                    // 一定要调用，加载失败
                                    page.finishLoad(false);
                                }
                            }
                        });
            }
        };
    }

    //获取列表数据
    public void getData() {
        page.loadPage(true);
    }

    //点击事件
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.float_btn_main:
                IntentUtil.redirectFinestWebView(mContext, Constant.GITHUB_URL);
                break;
        }
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
