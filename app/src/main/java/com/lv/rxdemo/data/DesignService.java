package com.lv.rxdemo.data;

import com.lv.rxdemo.config.Constant;
import com.lv.rxdemo.model.ModelDesignData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lv on 2016/7/18.
 */
public interface DesignService {

    @GET(Constant.MODEL_DESIGN_URL)
    Observable<ModelDesignData> getModelDesignData(@Query("vb") int modelDesignConfig, @Query("cpn") int currentPageNo);
}
