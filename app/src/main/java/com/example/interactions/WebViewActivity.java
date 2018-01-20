package com.example.interactions;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.R;
import com.example.databinding.WebviewBinding;

public class WebViewActivity extends AppCompatActivity {
	public static final String TITLE_PAGE = "TITLE_PAGE";
	public static final String URL_PAGE = "URL_PAGE";

	private String title;
	private String url;

	WebviewBinding binding;

	public static Intent newIntent(@NonNull Context context, @StringRes int title, @StringRes int url) {
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra(TITLE_PAGE, title);
		intent.putExtra(URL_PAGE, url);

		return intent;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.webview);

		if (getIntent().getExtras() == null) {
			throw new IllegalStateException("WebViewActivity must be initialized with title and url extras.");
		}

		title = getIntent().getExtras().getString(TITLE_PAGE);
		url = getIntent().getExtras().getString(URL_PAGE);

		setSupportActionBar(binding.toolbar);

		final ActionBar supportActionBar = getSupportActionBar();

		if (supportActionBar == null) {
			throw new IllegalStateException("failed to setup supportActionBar. supportActionBar is null.");
		}

		supportActionBar.setDisplayHomeAsUpEnabled(true);
		supportActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back));
		supportActionBar.setTitle(title);

		setupWebView();

		binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	private void setupWebView() {
		binding.webview.loadUrl(url);
		binding.webview.setWebViewClient(new WebViewClient() {
			@SuppressWarnings("deprecation")
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
				return true;
			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(URL_PAGE, url);
		outState.putString(TITLE_PAGE, title);
	}
}
