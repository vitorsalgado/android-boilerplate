package com.example;

import com.example.api.HttpClientWithPinningProvider;

import okhttp3.OkHttpClient;

class PerBuildComponentProvider {
	private static PerBuildComponentProvider instance;

	static PerBuildComponentProvider getInstance() {
		if (instance == null) {
			instance = new PerBuildComponentProvider();
		}

		return instance;
	}

	OkHttpClient.Builder okHttpBuilder() {
		return HttpClientWithPinningProvider.getClient().newBuilder();
	}
}
