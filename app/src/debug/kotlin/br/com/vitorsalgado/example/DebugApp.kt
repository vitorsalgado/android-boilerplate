package br.com.vitorsalgado.example

import android.os.StrictMode
import br.com.vitorsalgado.example.trackers.ActivityDebugLifecycleTracker
import br.com.vitorsalgado.example.trackers.FragmentDebugLifecycleTracker
import br.com.vitorsalgado.example.utils.LogUtility
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tspoon.traceur.Traceur
import timber.log.Timber

class DebugApp : App() {
  override fun onCreate() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }

    super.onCreate()

    Stetho.initialize(
      Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build()
    )

    trackActivitiesAndFragmentsLifecycleForDebug()
    Traceur.enableLogging()
    Timber.plant(Timber.DebugTree())

    enableStrictMode()
  }

  override fun onTrimMemory(level: Int) {
    super.onTrimMemory(level)
    LogUtility.d("[ onTrimMemory ] $level")
  }

  override fun enableLeakCanary(): RefWatcher {
    return LeakCanary.refWatcher(this).buildAndInstall()
  }

  private fun enableStrictMode() {
    StrictMode.setThreadPolicy(
      StrictMode.ThreadPolicy.Builder()
        .detectAll()
        .penaltyFlashScreen()
        .penaltyLog()
        .build()
    )

    StrictMode.setVmPolicy(
      StrictMode.VmPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .build()
    )
  }

  private fun trackActivitiesAndFragmentsLifecycleForDebug() {
    val fragmentLifecycleTracker = FragmentDebugLifecycleTracker()
    val activityLifecycleTracker = ActivityDebugLifecycleTracker(fragmentLifecycleTracker)

    registerActivityLifecycleCallbacks(activityLifecycleTracker)
  }
}
