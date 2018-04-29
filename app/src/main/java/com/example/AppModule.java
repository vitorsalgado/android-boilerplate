package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.api.gateway.Api;
import com.example.api.gateway.ApiBuilder;
import com.example.api.gateway.Config;

import java.io.File;

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

  @Provides
  @Singleton
  Api provideApi(@NonNull OkHttpClient.Builder okBuilder, @NonNull Gson gson) {
    String uri = BuildConfig.API_URI;
    String cache = "com.example.network.cache";
    Config config = new Config(uri, new File(cache), cache, 100 * 1024 * 1024);

    return ApiBuilder.build(okBuilder, gson, config);
  }
}
