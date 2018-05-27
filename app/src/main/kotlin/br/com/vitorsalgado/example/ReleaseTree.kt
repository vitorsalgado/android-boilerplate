package br.com.vitorsalgado.example

import android.util.Log.ERROR
import android.util.Log.INFO
import android.util.Log.WARN
import com.crashlytics.android.Crashlytics
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    when (priority) {
      INFO -> Crashlytics.log(priority, tag, message)
      WARN, ERROR -> Crashlytics.logException(t)
    }
  }
}
