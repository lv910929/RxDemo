package com.lv.rxdemo.data;

import com.lv.rxdemo.config.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lv on 2016/7/18.
 */
public class DesignFactory {

    public static DesignService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(DesignService.class);
    }
}
