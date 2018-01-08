package com.example.config;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.example.analytics.AnalyticsActivityLifecycle;

public final class AnalyticsConfig {
	public static void setup(@NonNull Application application) {
		if (BuildConfig.DEBUG) {
			return;
		}

		application.registerActivityLifecycleCallbacks(new AnalyticsActivityLifecycle());
		FirebaseAnalytics.getInstance(application).setAnalyticsCollectionEnabled(!BuildConfig.DEBUG);
	}
}
