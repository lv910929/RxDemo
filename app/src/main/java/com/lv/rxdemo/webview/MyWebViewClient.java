package com.lv.rxdemo.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigkoo.svprogresshud.SVProgressHUD;

public class MyWebViewClient extends WebViewClient {

    private Context context;

    private SVProgressHUD progressHUD;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url != null && url.startsWith("mailto:")
                || url.startsWith("geo:") || url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            view.loadUrl(url);
        }
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        Log.d("webview", "正在加载......");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        Log.d("webview", "加载完成......");
        if (!view.getSettings().getLoadsImagesAutomatically()) {
            view.getSettings().setLoadsImagesAutomatically(true);
        }
        if (progressHUD.isShowing()) {
            progressHUD.dismiss();
        }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (progressHUD == null) {
            progressHUD = new SVProgressHUD(context);
            progressHUD.showWithStatus("加载中...", SVProgressHUD.SVProgressHUDMaskType.Clear);
        }
        /*if (url != null && url.startsWith("mailto:")
                || url.startsWith("geo:") || url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            view.loadUrl(url);
        } else if (url.contains("http://www.bdhome.cn/product")) {
            IntentUtil.redirectToProduct(context, Long.getLong("21474860063"));
            ((Activity) context).finish();
        }*/
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        // TODO Auto-generated method stub
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        // TODO Auto-generated method stub
        super.onReceivedError(view, errorCode, description, failingUrl);
        //view.loadUrl("file:///android_asset/day1.html");
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend,
                                   Message resend) {
        // TODO Auto-generated method stub
        super.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                   SslError error) {
        // TODO Auto-generated method stub
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view,
                                          HttpAuthHandler handler, String host, String realm) {
        // TODO Auto-generated method stub
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        // TODO Auto-generated method stub
        return super.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        // TODO Auto-generated method stub
        super.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm,
                                       String account, String args) {
        // TODO Auto-generated method stub
        super.onReceivedLoginRequest(view, realm, account, args);
    }

}
