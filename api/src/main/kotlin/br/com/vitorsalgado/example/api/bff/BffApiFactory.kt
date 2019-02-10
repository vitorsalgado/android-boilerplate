package br.com.vitorsalgado.example.api.bff

import br.com.vitorsalgado.example.api.ApiAdapterFactory
import com.google.gson.Gson
import io.reactivex.annotations.NonNull
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object BffApiFactory {
  internal var bffApi: BffApi? = null

  operator fun get(
    @NonNull okBuilder: OkHttpClient.Builder,
    @NonNull gson: Gson,
    @NonNull config: Config
  ): BffApi {

    if (bffApi == null) {
      bffApi = build(okBuilder, gson, config)
    }

    return bffApi as BffApi
  }

  fun build(
    @NonNull okBuilder: OkHttpClient.Builder,
    @NonNull gson: Gson,
    @NonNull config: Config): BffApi {
    val httpCacheDirectory = File(config.cacheDir, config.cacheName)
    val cacheSize = config.cacheSize
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    okBuilder
      .cache(cache)
      .followRedirects(true)
      .followSslRedirects(true)

    return Retrofit.Builder()
      .baseUrl(config.uri)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(ApiAdapterFactory())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(okBuilder.build())
      .build()
      .create(BffApi::class.java)
  }
}
