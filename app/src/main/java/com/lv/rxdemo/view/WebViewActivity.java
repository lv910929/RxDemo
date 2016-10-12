package com.lv.rxdemo.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.widget.Toast;

import com.lv.rxdemo.R;
import com.lv.rxdemo.config.RxConfig;
import com.lv.rxdemo.databinding.ActivityWebViewBinding;
import com.lv.rxdemo.utils.FileUtils;
import com.lv.rxdemo.view.base.SwipeBackActivity;
import com.lv.rxdemo.viewmodel.WebViewModel;
import com.lv.rxdemo.webview.JavaScriptObject;
import com.lv.rxdemo.webview.MyWebChromeClient;
import com.lv.rxdemo.webview.MyWebViewClient;

import java.io.File;

public class WebViewActivity extends SwipeBackActivity implements SwipeRefreshLayout.OnRefreshListener, MyWebChromeClient.OpenFileChooserCallBack, MyWebChromeClient.LollipopFileCallBack {

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CHOOSE = 2;
    public static final int LOAD_FINISH_FLAG = 0;

    private ActivityWebViewBinding activityWebViewBinding;

    private WebViewModel webViewModel;

    public static Handler handler;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessagesAboveL;
    private Uri cameraUri;
    private String loadUrl;
    private String title;

    private JavaScriptObject javaScriptObject;
    private MyWebViewClient myWebViewClient;
    private MyWebChromeClient myWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTheme();
        initDataBinding();
        initToolBar(title, true);
        setWebView();
        setRefreshLayout();
        initHandler();
    }

    @Override
    protected void initData() {
        super.initData();
        javaScriptObject = new JavaScriptObject(this);
        Bundle bundle = this.getIntent().getExtras();
        loadUrl = bundle.getString("loadUrl");
        title = bundle.getString("title");
    }

    @Override
    protected void initDataBinding() {
        activityWebViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        webViewModel = new WebViewModel(WebViewActivity.this);
        activityWebViewBinding.setWebViewModel(webViewModel);
        setStatusTransparent();
    }

    private void setWebView() {
        myWebChromeClient = new MyWebChromeClient(WebViewActivity.this, WebViewActivity.this);
        myWebViewClient = new MyWebViewClient(WebViewActivity.this);
        WebSettings webSettings = activityWebViewBinding.webView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 启用数据库
        webSettings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext()
                .getDir("database", MODE_PRIVATE).getPath();
        // 启用地理定位
        webSettings.setGeolocationEnabled(true);
        // 设置定位的数据库路径
        webSettings.setGeolocationDatabasePath(dir);
        webSettings.setDomStorageEnabled(true);
        activityWebViewBinding.webView.setWebViewClient(myWebViewClient);
        activityWebViewBinding.webView.setWebChromeClient(myWebChromeClient);
        activityWebViewBinding.webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= 19) {
            activityWebViewBinding.webView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            activityWebViewBinding.webView.getSettings().setLoadsImagesAutomatically(false);
        }
        addJavascriptInterface();
        activityWebViewBinding.webView.loadUrl(loadUrl);
    }

    private void addJavascriptInterface() {
        activityWebViewBinding.webView.addJavascriptInterface(javaScriptObject, "bluet");
    }

    //设置下拉刷新
    private void setRefreshLayout() {
        TypedValue primary = new TypedValue();//主题色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, primary, true);
        activityWebViewBinding.refreshLayout.setViewGroup(activityWebViewBinding.webView);
        activityWebViewBinding.refreshLayout.setColorSchemeResources(primary.resourceId);
        activityWebViewBinding.refreshLayout.setOnRefreshListener(this);
        activityWebViewBinding.refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                activityWebViewBinding.refreshLayout.setRefreshing(true);
            }
        });
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case LOAD_FINISH_FLAG:
                        activityWebViewBinding.refreshLayout.setRefreshing(false);
                        break;
                }
            }
        };
    }

    @Override
    public void onRefresh() {
        //下拉重新加载
        activityWebViewBinding.webView.reload();
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        if (mUploadMessage != null) return;
        mUploadMessage = uploadMsg;
        selectImage();
    }

    private void selectImage() {
        if (!FileUtils.checkSDcard(this)) {
            return;
        }
        String[] selectPicTypeStr = {"拍照", "图库"};
        new AlertDialog.Builder(this)
                .setOnCancelListener(new ReOnCancelListener())
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // 相机拍摄
                                        openCamera();
                                        break;
                                    case 1:// 手机相册
                                        chosePicture();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
    }

    /**
     * 本地相册选择图片
     */
    private void chosePicture() {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, REQUEST_CHOOSE);
    }

    /**
     * 选择照片后结束
     */
    private Uri afterChosePic(Intent data) {
        if (data != null) {
            final String path = data.getData().getPath();
            if (path != null && (path.endsWith(".png") || path.endsWith(".PNG") || path.endsWith(".jpg") || path.endsWith(".JPG"))) {
                return data.getData();
            } else {
                Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    /**
     * 打开照相机
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imagePaths = RxConfig.SDCARD_ROOT_PATH + RxConfig.SAVE_IMAGE_PATH + (System.currentTimeMillis() + ".png");
        // 必须确保文件夹路径存在，否则拍照后无法完成回调
        File vFile = new File(imagePaths);
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        cameraUri = Uri.fromFile(vFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }
        if (mUploadMessagesAboveL != null) {
            onActivityResultAboveL(requestCode, resultCode, data);
        }
        if (mUploadMessage == null) return;
        Uri uri = null;
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            if (data != null) {
                cameraUri = data.getData();
                uri = cameraUri;
            }
        }
        if (requestCode == REQUEST_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                uri = afterChosePic(data);
            }
        }
        mUploadMessage.onReceiveValue(uri);
        mUploadMessage = null;
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 5.0以后机型 返回文件选择
     */
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {

        Uri[] results = null;
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            results = new Uri[]{cameraUri};
        }
        if (requestCode == REQUEST_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                String dataString = data.getDataString();
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadMessagesAboveL.onReceiveValue(results);
        mUploadMessagesAboveL = null;
        return;
    }

    @Override
    public void lollipopFileCallBack(ValueCallback<Uri[]> filePathCallback) {
        if (mUploadMessagesAboveL != null) {
            mUploadMessagesAboveL.onReceiveValue(null);
        } else {
            mUploadMessagesAboveL = filePathCallback;
            selectImage();
        }
    }

    /**
     * dialog监听类
     */
    private class ReOnCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }

            if (mUploadMessagesAboveL != null) {
                mUploadMessagesAboveL.onReceiveValue(null);
                mUploadMessagesAboveL = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (activityWebViewBinding.webView != null) {
            activityWebViewBinding.webView.destroy();
        }
        super.onDestroy();
    }

}
