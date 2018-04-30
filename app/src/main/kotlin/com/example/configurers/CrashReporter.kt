package com.example.configurers

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.example.BuildConfig
import io.fabric.sdk.android.Fabric

object CrashReporter {
  fun setup(context: Context) {
    val crashlyticsKit = Crashlytics.Builder()
      .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
      .build()

    Fabric.with(context, crashlyticsKit)
  }
}
