package com.example

import com.crashlytics.android.Crashlytics
import com.example.android.utils.LogUtility

import io.fabric.sdk.android.Fabric

object Reporter {
  fun report(throwable: Throwable) {
    LogUtility.e(throwable)

    if (BuildConfig.DEBUG) {
      return
    }

    if (Fabric.isInitialized()) {
      Crashlytics.logException(throwable)
    }
  }
}
