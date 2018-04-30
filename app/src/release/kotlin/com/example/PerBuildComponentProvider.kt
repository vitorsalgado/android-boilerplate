package com.example

import com.example.api.HttpClientWithPinningProvider

import okhttp3.OkHttpClient

internal class PerBuildComponentProvider {
  fun okHttpBuilder(): OkHttpClient.Builder {
    return HttpClientWithPinningProvider.client.newBuilder()
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
