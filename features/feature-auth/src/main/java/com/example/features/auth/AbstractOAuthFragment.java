//package com.example.features.auth;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebView;
//
//import com.example.features.AbstractWebViewFragment;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.nio.charset.Charset;
//
//public abstract class AbstractOAuthFragment extends AbstractWebViewFragment implements OAuthView {
//	private OAuthPresenter presenter;
//	private String url;
//	private boolean resultOK;
//
//	protected abstract String getOAuthServiceName();
//
//	protected abstract String generateURL(LoginServiceConfiguration oauthConfig);
//
//	protected final String getStateString() {
//		try {
//			return Base64.encodeToString(new JSONObject().put("loginStyle", "popup")
//				.put("credentialToken", getOAuthServiceName() + System.currentTimeMillis())
//				.put("isCordova", true)
//				.toString()
//				.getBytes(Charset.forName("UTF-8")), Base64.NO_WRAP);
//		} catch (JSONException exception) {
//			throw new RuntimeException(exception);
//		}
//	}
//
//	@Nullable
//	@Override
//	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		super.onCreateView(inflater, container, savedInstanceState);
//
//		presenter = new OAuthPresenter(this);
//		presenter.loadService(getOAuthServiceName());
//
//		return binding.getRoot();
//	}
//
//	@Override
//	protected void onPageLoaded(WebView webview, String url) {
//		super.onPageLoaded(webview, url);
//
//		if (url.contains("_oauth/" + getOAuthServiceName() + "?close")) {
//			final String jsHookUrl = "javascript:"
//				+ "window._synastry_hook.handleConfig(document.getElementById('config').innerText);";
//			webview.loadUrl(jsHookUrl);
//		}
//	}
//
//	@Override
//	public void showService(LoginServiceConfiguration oauthConfig) {
//		url = generateURL(oauthConfig);
//
//		showWebView();
//	}
//
//	@Override
//	public void close() {
//		getActivity().finish();
//	}
//
//	@Override
//	public void showLoginDone() {
//		resultOK = true;
//		onOAuthCompleted();
//	}
//
//	@Override
//	public void showLoginError() {
//		onOAuthCompleted();
//	}
//
//	private void showWebView() {
//		if (TextUtils.isEmpty(url)) {
//			getActivity().finish();
//			return;
//		}
//
//		final WebView webView = getWebview();
//
//		if (webView == null) {
//			getActivity().finish();
//			return;
//		}
//
//		resultOK = false;
//		webView.getSettings().setUserAgentString("Chrome/56.0.0.0 Mobile");
//		webView.loadUrl(url);
//		webView.addJavascriptInterface(new JSInterface(result -> {
//			// onPageFinish is called twice... Should ignore latter one.
//			if (resultOK) {
//				return;
//			}
//
//			//presenter.login(result);
//		}), "_example_hook");
//	}
//
//	protected void onOAuthCompleted() {
//	}
//
//	@Override
//	protected void navigateToInitialPage(WebView webview) {
//
//	}
//
//	@FunctionalInterface
//	private interface JSInterfaceCallback {
//		void handleResult(@Nullable JSONObject result);
//	}
//
//	private static final class JSInterface {
//		private final JSInterfaceCallback jsInterfaceCallback;
//
//		JSInterface(JSInterfaceCallback callback) {
//			jsInterfaceCallback = callback;
//		}
//
//		@JavascriptInterface
//		public void handleConfig(String config) {
//			try {
//				jsInterfaceCallback.handleResult(new JSONObject(config));
//			} catch (Exception exception) {
//				jsInterfaceCallback.handleResult(null);
//			}
//		}
//	}
//}
