package com.example;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.flowup.FlowUp;

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

		FlowUp.Builder.with(this)
				.apiKey(BuildConfig.FLOWUP_KEY)
				.forceReports(BuildConfig.DEBUG)
				.start();
	}

	@Override
	protected RefWatcher setUpLeakCanary() {
		return LeakCanary.refWatcher(this)
				.buildAndInstall();
	}

}
