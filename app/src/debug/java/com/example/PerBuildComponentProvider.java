package com.example;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

class PerBuildComponentProvider {
  private static PerBuildComponentProvider instance;

  static PerBuildComponentProvider getInstance() {
    if (instance == null) {
      instance = new PerBuildComponentProvider();
    }

    return instance;
  }

  OkHttpClient.Builder okHttpBuilder() {
    return new OkHttpClient.Builder()
      .addNetworkInterceptor(new StethoInterceptor())
      .addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
  }
}
