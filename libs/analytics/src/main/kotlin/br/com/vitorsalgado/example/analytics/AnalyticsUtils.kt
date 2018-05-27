package br.com.vitorsalgado.example.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsUtils {
  @JvmOverloads
  fun trackAction(context: Context, @Action action: String, extras: Bundle? = null) {
    FirebaseAnalytics.getInstance(context).logEvent(action, extras)
  }

  fun trackView(activity: Activity, @Screen screen: String) {
    FirebaseAnalytics.getInstance(activity).setCurrentScreen(activity, screen, null)
  }
}
