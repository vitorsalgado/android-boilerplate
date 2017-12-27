package com.example;

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
		return new OkHttpClient.Builder();
	}
}
