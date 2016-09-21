package com.lv.rxdemo.webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient{

	private OpenFileChooserCallBack mOpenFileChooserCallBack;

	private LollipopFileCallBack lollipopFileCallBack;

	public MyWebChromeClient(OpenFileChooserCallBack mOpenFileChooserCallBack, LollipopFileCallBack lollipopFileCallBack) {
		this.mOpenFileChooserCallBack = mOpenFileChooserCallBack;
		this.lollipopFileCallBack = lollipopFileCallBack;
	}

	@Override
	public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
		callback.invoke(origin, true, false);
		super.onGeolocationPermissionsShowPrompt(origin, callback);
	}

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		// TODO Auto-generated method stub
		Log.d("hujun","newProgress--->"+newProgress);
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		// TODO Auto-generated method stub
		super.onReceivedTitle(view, title);
	}

	@Override
	public void onReceivedIcon(WebView view, Bitmap icon) {
		// TODO Auto-generated method stub
		super.onReceivedIcon(view, icon);
	}

	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsAlert(view, url, message, result);
	}

	@Override
	public boolean onJsConfirm(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsConfirm(view, url, message, result);
	}

	@Override
	public boolean onJsPrompt(WebView view, String url, String message,
			String defaultValue, JsPromptResult result) {
		// TODO Auto-generated method stub
		return super.onJsPrompt(view, url, message, defaultValue, result);
	}

	@Override
	public boolean onJsBeforeUnload(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsBeforeUnload(view, url, message, result);
	}

	@Override
	public boolean onJsTimeout() {
		// TODO Auto-generated method stub
		return super.onJsTimeout();
	}

	// For Android 3.0+
	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
		mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
	}

	// For Android < 3.0
	public void openFileChooser(ValueCallback<Uri> uploadMsg) {
		openFileChooser(uploadMsg, "");
	}

	// For Android  > 4.1.1
	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
		openFileChooser(uploadMsg, acceptType);
	}

	// For Android 5.0
	@Override
	public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

		lollipopFileCallBack.lollipopFileCallBack(filePathCallback);
		return true;
	}

	public interface OpenFileChooserCallBack {
		void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);
	}

	public interface LollipopFileCallBack {
		void lollipopFileCallBack(ValueCallback<Uri[]> filePathCallback);
	}
}
