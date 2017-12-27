package com.example.interactions;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.R;
import com.example.databinding.WebviewBinding;

public abstract class AbstractWebViewFragment extends AbstractFragment {
	private boolean isSet = false;
	protected WebviewBinding binding;

	private WebViewClient webviewClient = new WebViewClient() {
		private boolean error;

		@Override
		public void onPageStarted(WebView webview, String url, Bitmap favicon) {
			error = false;
		}

		@Override
		public void onPageFinished(WebView webview, String url) {
			if (!error) {
				onPageLoaded(webview, url);
			}
		}

		@Override
		public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
			super.onReceivedError(view, request, error);
			this.error = true;
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView webview, String url) {
			return (shouldOverride(webview, url) && onHandleCallback(webview, url))
				|| super.shouldOverrideUrlLoading(webview, url);
		}

		@Override
		public void onFormResubmission(WebView view, Message dontResend, Message resend) {
			//resend POST request without confirmation.
			resend.sendToTarget();
		}
	};

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.webview, container, false);

		setupWebView();
		navigateToInitialPage(binding.webview);

		return binding.getRoot();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setupWebView() {
		if (isSet) {
			return;
		}

		WebSettings settings = binding.webview.getSettings();

		if (settings != null) {
			settings.setJavaScriptEnabled(true);
		}

		binding.webview.setHorizontalScrollBarEnabled(false);
		binding.webview.setWebViewClient(webviewClient);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
			//refs: https://code.google.com/p/android/issues/detail?id=35288
			binding.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

		isSet = true;
	}

	protected WebView getWebview() {
		setupWebView();
		return binding.webview;
	}

	protected abstract void navigateToInitialPage(WebView webview);

	protected void onPageLoaded(WebView webview, String url) {
	}

	protected boolean shouldOverride(WebView webview, String url) {
		return false;
	}

	protected boolean onHandleCallback(WebView webview, String url) {
		return false;
	}
}
