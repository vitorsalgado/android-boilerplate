package com.example.data.net.api.interceptors;

import com.example.App;
import com.example.AppContext;
import com.example.AppDefaults;
import com.example.data.net.api.Api;
import com.example.data.net.api.RefreshHandler;
import com.example.data.net.api.dtos.TokenResponse;
import com.example.utils.AppUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
	private static final String VERSION_NAME = AppUtils.getVersionName(App.getContext());
	private static final String API_NAMESPACE = "com.example";
	private final Object lock = new Object();

	private RefreshHandler refreshHandler;

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request original = chain.request();

		if (AppContext.isAccessTokenExpired()) {
			synchronized (lock) {
				refreshHandler.refreshToken();
			}
		}

		Request request = original
				.newBuilder()
				.header("x-channel", AppDefaults.Channel)
				.header("x-app-version", VERSION_NAME)
				.header("accept", acceptHeader(1))
				.header("authorization", String.format("Bearer %s", AppContext.getAccessToken()))
				.method(original.method(), original.body())
				.build();

		return chain.proceed(request);
	}

	private static String acceptHeader(int version) {
		return String.format(Locale.US, "application/vnd.%s.%d+json", API_NAMESPACE, version);
	}

	public void setRefreshHandler(RefreshHandler refreshHandler) {
		this.refreshHandler = refreshHandler;
	}
}
