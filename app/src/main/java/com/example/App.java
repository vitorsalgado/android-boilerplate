package com.example;

import com.google.firebase.FirebaseApp;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.support.annotation.NonNull;

import com.example.configurers.Analytics;
import com.example.configurers.CrashLytics;
import com.example.configurers.FrescoPipelines;
import com.example.configurers.RemoteConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public abstract class App extends Application implements HasActivityInjector {
  public static final String SHARED_PREFERENCES = "com.example.shared_prefs";
  public static final String DATABASE = "com.example.database";

  protected static App instance;
  protected static RefWatcher refWatcher;
  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @NonNull
  public static App getInstance() {
    return instance;
  }

  public static RefWatcher getRefWatcher() {
    return refWatcher;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    setupDependenciesManager();

    final ImagePipelineConfig.Builder fresco = FrescoPipelines.setupFresco(this);
    trackFresco(fresco);
    Fresco.initialize(this, fresco.build());

    FirebaseApp.initializeApp(this);
    CrashLytics.setup(this);
    RemoteConfig.setup();
    Analytics.setup(this);

    refWatcher = enableLeakCanary();
    instance = this;
  }

  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);

    switch (level) {
      case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
      case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
      case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
      case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
      case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
      case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
      case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
        if (!Fresco.hasBeenInitialized()) {
          return;
        }

        Fresco.getImagePipeline().clearMemoryCaches();
        break;
    }
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }

  protected void setupDependenciesManager() {
    DaggerAppComponent
      .builder()
      .application(this)
      .build()
      .inject(this);
  }

  protected RefWatcher enableLeakCanary() {
    return RefWatcher.DISABLED;
  }

  protected void trackFresco(ImagePipelineConfig.Builder builder) {
    // DebugApp should override and properly implement this
  }
}
