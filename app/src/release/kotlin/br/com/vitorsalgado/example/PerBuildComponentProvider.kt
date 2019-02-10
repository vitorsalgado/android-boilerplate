package br.com.vitorsalgado.example

import br.com.vitorsalgado.example.api.HttpClientWithPinningProvider
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker

import okhttp3.OkHttpClient

internal class PerBuildComponentProvider {
  fun okHttpBuilder(): OkHttpClient.Builder {
    return HttpClientWithPinningProvider.client.newBuilder()
  }

  fun imageCacheStatsTracker(): ImageCacheStatsTracker? {
    return null
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
