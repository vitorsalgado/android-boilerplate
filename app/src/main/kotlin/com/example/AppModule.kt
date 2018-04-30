package com.example

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.api.gateway.Api
import com.example.api.gateway.ApiBuilder
import com.example.api.gateway.Config
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

@Module
internal class AppModule {
  @Provides
  @Singleton
  fun provideContext(application: Application): Context {
    return application
  }

  @Provides
  @Singleton
  fun provideSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences(App.SHARED_PREFERENCES, Context.MODE_PRIVATE)
  }

  @Provides
  @Singleton
  fun provideGson(): Gson {
    return GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
      .create()
  }

  @Provides
  @Singleton
  fun provideOkBuilder(): OkHttpClient.Builder {
    return PerBuildComponentProvider.getInstance().okHttpBuilder()
  }

  @Provides
  @Singleton
  fun provideApi(okBuilder: OkHttpClient.Builder, gson: Gson): Api {
    val uri = BuildConfig.API_URI
    val cache = "com.example.network.cache"
    val config = Config(uri, File(cache), cache, 100 * 1024 * 1024)

    return ApiBuilder.build(okBuilder, gson, config)
  }
}
