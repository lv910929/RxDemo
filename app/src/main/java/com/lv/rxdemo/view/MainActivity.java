package com.lv.rxdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ActivityMainBinding;
import com.lv.rxdemo.model.MessageEvent;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.utils.DayNight;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.viewmodel.MainViewModel;
import com.lv.rxdemo.viewmodel.MainViewModelContact;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;


public class MainActivity extends BaseActivity implements MainViewModelContact.MainView {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private MainViewModelContact.MainView mainView = this;

    private DesignAdapter designAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initData();
        initTheme();
        initDataBinding();
        activityMainBinding.toolbar.setTitle("BDHOME");
        setSupportActionBar(activityMainBinding.toolbar);
        setupRecyclerMain();
    }

    //初始化DataBinding
    protected void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainViewModel = new MainViewModel(mainView, getContext());
        activityMainBinding.setMainViewModel(mainViewModel);
    }

    private void setupRecyclerMain() {
        activityMainBinding.recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        designAdapter = new DesignAdapter();
        activityMainBinding.recyclerMain.setAdapter(new SlideInBottomAnimationAdapter(designAdapter));//添加adapter滑动动画
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
        if (mDayNightHelper.isDay()) {//说明是日间模式
            menu.getItem(0).setIcon(R.drawable.ic_switch_night);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_switch_day);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_mode:
                showAnimation();
                if (mDayNightHelper.isDay()) {//说明是日间模式
                    item.setIcon(R.drawable.ic_switch_day);
                    mDayNightHelper.setMode(DayNight.NIGHT);
                    setTheme(R.style.NightTheme);
                } else {
                    item.setIcon(R.drawable.ic_switch_night);
                    mDayNightHelper.setMode(DayNight.DAY);
                    setTheme(R.style.DayTheme);
                }
                refreshUI();
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue primary = new TypedValue();//主题色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.commBackground, background, true);
        theme.resolveAttribute(R.attr.commTextColor, textColor, true);
        theme.resolveAttribute(R.attr.colorPrimary, primary, true);
        Resources resources = getResources();

        activityMainBinding.layoutMain.setBackgroundResource(background.resourceId);
        activityMainBinding.toolbar.setBackgroundResource(primary.resourceId);
        activityMainBinding.floatBtnMain.setColorNormalResId(primary.resourceId);
        activityMainBinding.floatBtnMain.setColorPressedResId(primary.resourceId);

        int childCount = activityMainBinding.recyclerMain.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) activityMainBinding.recyclerMain.getChildAt(childIndex);
            childView.setBackgroundResource(background.resourceId);
            View infoLayout = childView.findViewById(R.id.cardview_item_design);
            infoLayout.setBackgroundResource(background.resourceId);

            TextView apartmentName = (TextView) childView.findViewById(R.id.text_apartment_name);
            apartmentName.setBackgroundResource(background.resourceId);
            apartmentName.setTextColor(resources.getColor(textColor.resourceId));

            TextView constructionArea = (TextView) childView.findViewById(R.id.text_construction_area);
            constructionArea.setBackgroundResource(background.resourceId);
            constructionArea.setTextColor(resources.getColor(textColor.resourceId));

            TextView apartLayout = (TextView) childView.findViewById(R.id.text_apartment_layout);
            apartLayout.setBackgroundResource(background.resourceId);
            apartLayout.setTextColor(resources.getColor(textColor.resourceId));

            TextView budgetPrice = (TextView) childView.findViewById(R.id.text_budget_price);
            budgetPrice.setBackgroundResource(background.resourceId);
            budgetPrice.setTextColor(resources.getColor(textColor.resourceId));
        }

        //让 RecyclerView 缓存在 Pool 中的 Item 失效
        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到 AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField.get(activityMainBinding.recyclerMain), new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool = activityMainBinding.recyclerMain.getRecycledViewPool();
            recycledViewPool.clear();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case 1://关闭
                finish();
                break;
        }
    }

    private long firstTime;

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Snackbar sb = Snackbar.make(activityMainBinding.layoutMain, "再按一次退出", Snackbar.LENGTH_SHORT);
            sb.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            sb.show();
            firstTime = secondTime;
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
        EventBus.getDefault().unregister(this);
    }
}
