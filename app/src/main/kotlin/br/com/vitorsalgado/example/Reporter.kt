package br.com.vitorsalgado.example

import br.com.vitorsalgado.example.utils.LogUtility
import com.crashlytics.android.Crashlytics
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
