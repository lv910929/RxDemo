package com.lv.rxdemo.viewmodel;

import android.content.Context;

import com.lv.rxdemo.model.VRModel;

import java.util.List;

/**
 * Created by Lv on 2016/7/19.
 */
public interface MainViewModelContact {

    interface MainView {

        Context getContext();

        void loadData(List<VRModel> vrModels);
    }

    interface ViewModel {

        void destroy();
    }
}
