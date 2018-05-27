package br.com.vitorsalgado.example.configurers

import android.app.Application
import br.com.vitorsalgado.example.BuildConfig
import br.com.vitorsalgado.example.analytics.AnalyticsActivityLifecycle
import com.google.firebase.analytics.FirebaseAnalytics

object Analytics {
  fun setup(application: Application) {
    application.registerActivityLifecycleCallbacks(AnalyticsActivityLifecycle())
    FirebaseAnalytics.getInstance(application).setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)
  }
}
