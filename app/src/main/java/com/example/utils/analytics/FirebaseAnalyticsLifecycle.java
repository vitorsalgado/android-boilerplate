package com.example.utils.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticsLifecycle implements Application.ActivityLifecycleCallbacks {
	private final FirebaseAnalytics mFirebaseAnalytics;

	public FirebaseAnalyticsLifecycle(@NonNull Context context) {
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle bundle) {

	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {

	}

	@Override
	public void onActivityPaused(Activity activity) {

	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}
}
