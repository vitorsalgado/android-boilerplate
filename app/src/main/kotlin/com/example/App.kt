package com.example

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import com.example.configurers.Analytics
import com.example.configurers.FrescoPipelines
import com.example.configurers.RemoteConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.google.firebase.FirebaseApp
import com.squareup.leakcanary.RefWatcher
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

abstract class App : Application(), HasActivityInjector {
  companion object {
    const val SHARED_PREFERENCES = "com.example.shared_prefs"
    const val DATABASE = "com.example.database"

    lateinit var instance: App protected set
    lateinit var refWatcher: RefWatcher protected set
  }

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    setupDependenciesManager()

    val fresco = FrescoPipelines.setup(this)
    trackFresco(fresco)
    Fresco.initialize(this, fresco.build())

    FirebaseApp.initializeApp(this)
    RemoteConfig.setup()
    Analytics.setup(this)

    refWatcher = enableLeakCanary()
    instance = this
  }

  override fun onTrimMemory(level: Int) {
    super.onTrimMemory(level)

    when (level) {
      ComponentCallbacks2.TRIM_MEMORY_COMPLETE, ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW, ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL, ComponentCallbacks2.TRIM_MEMORY_BACKGROUND, ComponentCallbacks2.TRIM_MEMORY_MODERATE, ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE, ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
        if (!Fresco.hasBeenInitialized()) {
          return
        }

        Fresco.getImagePipeline().clearMemoryCaches()
      }
    }
  }

  override fun activityInjector(): AndroidInjector<Activity>? {
    return dispatchingAndroidInjector
  }

  private fun setupDependenciesManager() {
    DaggerAppComponent
      .builder()
      .application(this)
      .build()
      .inject(this)
  }

  protected open fun enableLeakCanary(): RefWatcher {
    return RefWatcher.DISABLED
  }

  protected open fun trackFresco(builder: ImagePipelineConfig.Builder) {
    // DebugApp should override and properly implement this
  }
}
