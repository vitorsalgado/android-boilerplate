package br.com.vitorsalgado.example

import android.os.StrictMode
import br.com.vitorsalgado.example.trackers.ActivityLifecycleTracker
import br.com.vitorsalgado.example.trackers.FragmentLifecycleTracker
import br.com.vitorsalgado.example.trackers.FrescoCacheStatsTracker
import br.com.vitorsalgado.example.utils.LogUtility
import com.facebook.imagepipeline.core.ImagePipelineConfig
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
        .build())

    trackActivitiesAndFragmentsLifecycle()
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
    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
      .detectAll()
      .penaltyFlashScreen()
      .penaltyLog()
      .penaltyDeath()
      .build())

    StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
      .detectAll()
      .penaltyLog()
      .penaltyDeath()
      .build())
  }

  override fun trackFresco(builder: ImagePipelineConfig.Builder) {
    builder.setImageCacheStatsTracker(FrescoCacheStatsTracker())
  }

  private fun trackActivitiesAndFragmentsLifecycle() {
    val fragmentLifecycleTracker = FragmentLifecycleTracker()
    val activityLifecycleTracker = ActivityLifecycleTracker(fragmentLifecycleTracker)

    registerActivityLifecycleCallbacks(activityLifecycleTracker)
  }
}
