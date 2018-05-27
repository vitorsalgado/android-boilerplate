package br.com.vitorsalgado.example.api.graphapi

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object GraphApiBuilder {
  private var graphApi: GraphApi? = null

  operator fun get(config: Config, okBuilder: OkHttpClient.Builder): GraphApi {
    if (graphApi == null) {
      graphApi = build(config, okBuilder)
    }

    return graphApi as GraphApi
  }

  private fun build(config: Config, okBuilder: OkHttpClient.Builder): GraphApi {
    val httpCacheDirectory = File(config.cacheDir)
    val cache = Cache(httpCacheDirectory, config.cacheSize.toLong())

    okBuilder.cache(cache)

    return Retrofit.Builder()
      .baseUrl(config.uri)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(okBuilder.build())
      .build()
      .create(GraphApi::class.java)
  }
}
