package com.example.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.App;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
class AppModule {
	@Provides
	@Singleton
	Context provideContext(@NonNull Application application) {
		return application;
	}

	@Provides
	@Singleton
	SharedPreferences provideSharedPreferences(@NonNull Application application) {
		return application.getSharedPreferences(App.SHARED_PREFERENCES, Context.MODE_PRIVATE);
	}

	@Provides
	@Singleton
	Gson provideGson() {
		return new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
			.create();
	}

	@Provides
	@Singleton
	OkHttpClient.Builder provideOkBuilder() {
		return PerBuildComponentProvider.getInstance().okHttpBuilder();
	}
}
