package com.example;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.support.annotation.NonNull;

import com.example.config.AnalyticsConfig;
import com.example.config.CrashlyticsConfig;
import com.example.config.FrescoConfig;
import com.example.config.RemoteConfig;
import com.example.di.DaggerAppComponent;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crash.FirebaseCrash;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
	public static final String SHARED_PREFERENCES = "com.example.shared_prefs";
	public static final String DATABASE = "com.example.database";

	protected static App instance;
	protected static RefWatcher refWatcher;

	@NonNull
	public static App getInstance() {
		return instance;
	}

	@Inject
	DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

	@Override
	public void onCreate() {
		super.onCreate();

		final ImagePipelineConfig.Builder fresco = FrescoConfig.setupFresco(this);

		setupDependenciesManager();
		trackFresco(fresco);
		Fresco.initialize(this, fresco.build());
		FirebaseApp.initializeApp(this);
		FirebaseCrash.setCrashCollectionEnabled(!BuildConfig.DEBUG);

		CrashlyticsConfig.setup(this);
		RemoteConfig.setup();
		AnalyticsConfig.setup(this);

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

	public static RefWatcher getRefWatcher() {
		return refWatcher;
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
