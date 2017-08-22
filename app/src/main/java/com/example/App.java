package com.example;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.example.utils.analytics.AnalyticsActivityLifecycle;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

import io.fabric.sdk.android.Fabric;

public class App extends Application {
	static WeakReference<App> instance;
	RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();

		AppDeps.setUp(new DepsProvider());
		AppContext.setUp(this);

		setUpCrashlytics();
		setUpAnalytics();

		Fresco.initialize(this);

		refWatcher = setUpLeakCanary();
		instance = new WeakReference<>(this);
	}

	public static Context getContext() {
		return instance.get().getApplicationContext();
	}

	public static App get(Context context) {
		return (App) context.getApplicationContext();
	}

	public static RefWatcher getRefWatcher(Context context) {
		return ((App) context.getApplicationContext()).refWatcher;
	}

	protected void setUpCrashlytics() {
		if (BuildConfig.DEBUG) {
			return;
		}

		Fabric.with(this, new Crashlytics());
	}

	protected RefWatcher setUpLeakCanary() {
		return RefWatcher.DISABLED;
	}

	protected void setUpAnalytics() {
		if (BuildConfig.DEBUG) {
			return;
		}

		instance.get().registerActivityLifecycleCallbacks(new AnalyticsActivityLifecycle());

		FirebaseAnalytics.getInstance(getContext()).setAnalyticsCollectionEnabled(true);
	}
}
