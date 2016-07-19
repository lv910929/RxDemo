package com.lv.rxdemo.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lv.rxdemo.R;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.view.DesignActivity;

/**
 * Created by Lv on 2016/7/19.
 */
public class ItemViewModel extends BaseObservable {

    private Context mContext;

    private VRModel vrModel;

    public ItemViewModel(Context mContext, VRModel vrModel) {
        this.mContext = mContext;
        this.vrModel = vrModel;
    }

    public String getDesignerPic() {
        return vrModel.getDesignerPic();
    }

    public String getModelHomeApartmentDesignName() {
        return vrModel.getModelHomeApartmentDesignName();
    }

    public String getConstructionArea() {
        return vrModel.getConstructionArea() + "";
    }

    public String getApartmentLayout() {
        return vrModel.getApartmentLayout();
    }

    public String getBudget() {
        return vrModel.getBudget() + "";
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(mContext, DesignActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DesignActivity.EXTRA_DESIGN, vrModel);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
    }

    public void setVrModel(VRModel vrModel) {
        this.vrModel = vrModel;
        notifyChange();
    }
}
