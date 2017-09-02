package com.example;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;

public class AppDebug extends App {
	@Override
	public void onCreate() {
		super.onCreate();

		if (LeakCanary.isInAnalyzerProcess(this)) {
			return;
		}

		FacebookSdk.setIsDebugEnabled(true);
		FacebookSdk.addLoggingBehavior(LoggingBehavior.DEVELOPER_ERRORS);

		Stetho.initializeWithDefaults(this);
	}

	@Override
	protected RefWatcher setUpLeakCanary() {
		return LeakCanary.refWatcher(this)
				.buildAndInstall();
	}

	@Override
	protected void setUpCrashlytics() {
		if (BuildConfig.DEBUG) {
			return;
		}

		Fabric.with(this, new Crashlytics());
	}
}
