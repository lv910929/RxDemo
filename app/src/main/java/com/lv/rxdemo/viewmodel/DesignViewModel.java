package com.lv.rxdemo.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lv.rxdemo.R;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.utils.IntentUtil;

/**
 * Created by Lv on 2016/7/18.
 */
public class DesignViewModel {

    private Context context;

    private VRModel vrModel;

    public DesignViewModel(Context context, VRModel vrModel) {
        this.context = context;
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

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.float_btn_design:
                IntentUtil.redirectWebView(context, "3D看房", vrModel.getModelHomeDesignVrPicAddress());
                break;
        }
    }
}
