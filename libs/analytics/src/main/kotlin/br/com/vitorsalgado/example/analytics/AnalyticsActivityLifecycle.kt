package br.com.vitorsalgado.example.analytics

import android.app.Activity
import android.app.Application
import android.os.Bundle

class AnalyticsActivityLifecycle : Application.ActivityLifecycleCallbacks {
  override fun onActivityPaused(activity: Activity?) {
  }

  override fun onActivityResumed(activity: Activity?) {
    if (activity is TraceableScreen) {
      AnalyticsUtils.trackView(activity, (activity as TraceableScreen).name)
    }
  }

  override fun onActivityStarted(activity: Activity?) {
  }

  override fun onActivityDestroyed(activity: Activity?) {
  }

  override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
  }

  override fun onActivityStopped(activity: Activity?) {
  }

  override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
  }
}
