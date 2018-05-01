package com.example.trackers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.utils.LogUtility

class ActivityLifecycleTracker(private val tracker: FragmentLifecycleTracker) : Application.ActivityLifecycleCallbacks {

  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
    LogUtility.d("Activity CREATED --> " + activity.javaClass.simpleName)

    if (activity is AppCompatActivity) {
      activity.supportFragmentManager
        .registerFragmentLifecycleCallbacks(tracker, false)
    }
  }

  override fun onActivityStarted(activity: Activity) {
    LogUtility.d("Activity STARTED --> " + activity.javaClass.simpleName)
  }

  override fun onActivityResumed(activity: Activity) {
    LogUtility.d("Activity RESUMED --> " + activity.javaClass.simpleName)
  }

  override fun onActivityPaused(activity: Activity) {
    LogUtility.d("Activity PAUSED --> " + activity.javaClass.simpleName)
  }

  override fun onActivityStopped(activity: Activity) {
    LogUtility.d("Activity STOPPED --> " + activity.javaClass.simpleName)
  }

  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    LogUtility.d("Activity SAVED INSTANCE STATE --> " + activity.javaClass.simpleName)
  }

  override fun onActivityDestroyed(activity: Activity) {
    LogUtility.d("Activity DESTROYED --> " + activity.javaClass.simpleName)

    if (activity is AppCompatActivity) {
      activity.supportFragmentManager
        .unregisterFragmentLifecycleCallbacks(tracker)
    }
  }
}
