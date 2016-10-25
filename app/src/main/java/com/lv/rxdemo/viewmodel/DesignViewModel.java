package com.lv.rxdemo.viewmodel;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.AlphaAnimation;
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

    public boolean isFabOpen = false;//判断fab菜单是否打开

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

    //打开菜单
    public void openMenu(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, -155, -135);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        view.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
        isFabOpen = true;
    }

    //关闭菜单
    public void closeMenu(View floatView, View emptyView) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(floatView, "rotation", -135, 20, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        emptyView.startAnimation(alphaAnimation);
        emptyView.setVisibility(View.GONE);
        isFabOpen = false;
    }
}
