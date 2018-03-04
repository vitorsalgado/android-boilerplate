package com.example.api.facebook.graphapi;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GraphApiBuilder {
	private static GraphApi graphApi = null;

	public static GraphApi get(Config config, OkHttpClient.Builder okBuilder) {
		if (graphApi == null) {
			graphApi = build(config, okBuilder);
		}

		return graphApi;
	}

	private static GraphApi build(Config config, OkHttpClient.Builder okBuilder) {
		File httpCacheDirectory = new File(config.getCacheDir());
		Cache cache = new Cache(httpCacheDirectory, config.getCacheSize());

		okBuilder.cache(cache);

		return new Retrofit.Builder()
			.baseUrl(config.getUri())
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(okBuilder.build())
			.build()
			.create(GraphApi.class);
	}
}
