package com.example.configurers

import android.app.Application
import com.example.BuildConfig
import com.example.analytics.AnalyticsActivityLifecycle
import com.google.firebase.analytics.FirebaseAnalytics

object Analytics {
  fun setup(application: Application) {
    application.registerActivityLifecycleCallbacks(AnalyticsActivityLifecycle())
    FirebaseAnalytics.getInstance(application).setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)
  }
}
