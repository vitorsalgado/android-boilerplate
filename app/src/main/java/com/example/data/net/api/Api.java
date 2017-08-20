package com.example.data.net.api;

import android.support.annotation.NonNull;

import com.example.AppDeps;
import com.example.BuildConfig;
import com.example.data.net.api.interceptors.RequestInterceptor;

import java.io.File;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import io.reactivex.Observable;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public interface Api {
	@POST("signOut")
	Observable<Void> signOut();

	class Builder {
		private static Api api = null;

		public static Api get(@NonNull String baseUrl, @NonNull File cacheDir) {
			if (api == null) {
				api = build(baseUrl, cacheDir);
			}

			return api;
		}

		private static Api build(@NonNull String baseUrl, @NonNull File cacheDir) {
			File httpCacheDirectory = new File(cacheDir, "responses");
			int cacheSize = 15 * 1024 * 1024;
			Cache cache = new Cache(httpCacheDirectory, cacheSize);

			final RequestInterceptor requestInterceptor = new RequestInterceptor();
			final TokenAuthenticator tokenAuthenticator = new TokenAuthenticator();
			final RefreshHandler refreshHandler = new RefreshHandler();

			final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
					.cache(cache)
					.followRedirects(true)
					.followSslRedirects(true)
					.addInterceptor(requestInterceptor)
					.authenticator(tokenAuthenticator);

			if (BuildConfig.DEBUG) {
				builder.addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
			}

			Api api = new Retrofit.Builder()
					.baseUrl(baseUrl)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
					.client(builder.build())
					.build()
					.create(Api.class);

			refreshHandler.setOAuthApi(AppDeps.oauthApi());
			tokenAuthenticator.setRefreshHandler(refreshHandler);
			requestInterceptor.setRefreshHandler(refreshHandler);

			return api;
		}
	}
}
