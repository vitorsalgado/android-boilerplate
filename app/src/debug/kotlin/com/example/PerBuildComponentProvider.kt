package com.example

import com.facebook.stetho.okhttp3.StethoInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

internal class PerBuildComponentProvider {
  fun okHttpBuilder(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
      .addNetworkInterceptor(StethoInterceptor())
      .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
  }

  companion object {
    private var instance: PerBuildComponentProvider? = null

    fun getInstance(): PerBuildComponentProvider {
      if (instance == null) {
        instance = PerBuildComponentProvider()
      }

      return instance as PerBuildComponentProvider
    }
  }
}
