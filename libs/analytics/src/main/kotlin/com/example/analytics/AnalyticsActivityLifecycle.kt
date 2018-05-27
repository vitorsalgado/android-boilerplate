package com.example.analytics

import android.app.Activity
import android.app.Application
import android.os.Bundle

class AnalyticsActivityLifecycle : Application.ActivityLifecycleCallbacks {
  override fun onActivityCreated(activity: Activity, bundle: Bundle) {
  }

  override fun onActivityStarted(activity: Activity) {
  }

  override fun onActivityResumed(activity: Activity) {
    if (activity is TraceableScreen) {
      AnalyticsUtils.trackView(activity, (activity as TraceableScreen).screenName)
    }
  }

  override fun onActivityPaused(activity: Activity) {
  }

  override fun onActivityStopped(activity: Activity) {
  }

  override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
  }

  override fun onActivityDestroyed(activity: Activity) {
  }
}
