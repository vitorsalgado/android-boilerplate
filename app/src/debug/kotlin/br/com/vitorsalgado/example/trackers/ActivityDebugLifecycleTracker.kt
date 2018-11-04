package br.com.vitorsalgado.example.trackers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import br.com.vitorsalgado.example.utils.LogUtility

class ActivityDebugLifecycleTracker(private val trackerDebug: FragmentDebugLifecycleTracker) : Application.ActivityLifecycleCallbacks {

  override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    LogUtility.d("Activity CREATED --> " + activity?.javaClass?.simpleName)

    if (activity is AppCompatActivity) {
      activity.supportFragmentManager
        .registerFragmentLifecycleCallbacks(trackerDebug, false)
    }
  }

  override fun onActivityStarted(activity: Activity?) {
    LogUtility.d("Activity STARTED --> " + activity?.javaClass?.simpleName)
  }

  override fun onActivityResumed(activity: Activity?) {
    LogUtility.d("Activity RESUMED --> " + activity?.javaClass?.simpleName)
  }

  override fun onActivityPaused(activity: Activity?) {
    LogUtility.d("Activity PAUSED --> " + activity?.javaClass?.simpleName)
  }

  override fun onActivityStopped(activity: Activity?) {
    LogUtility.d("Activity STOPPED --> " + activity?.javaClass?.simpleName)
  }

  override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    LogUtility.d("Activity SAVED INSTANCE STATE --> " + activity?.javaClass?.simpleName)
  }

  override fun onActivityDestroyed(activity: Activity?) {
    LogUtility.d("Activity DESTROYED --> " + activity?.javaClass?.simpleName)

    if (activity is AppCompatActivity) {
      activity.supportFragmentManager
        .unregisterFragmentLifecycleCallbacks(trackerDebug)
    }
  }
}
