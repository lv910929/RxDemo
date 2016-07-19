package com.lv.rxdemo.data;

import com.lv.rxdemo.config.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lv on 2016/7/18.
 */
public class DesignFactory {

    private static DesignService designService;

    private static OkHttpClient client;

    public static OkHttpClient initOkHttp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public static DesignService getService() {
        if (designService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(initOkHttp())
                    .baseUrl(Constant.MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            designService = retrofit.create(DesignService.class);
        }
        return designService;
    }
}
