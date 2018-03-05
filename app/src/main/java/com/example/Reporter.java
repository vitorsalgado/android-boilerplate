package com.example;

import com.crashlytics.android.Crashlytics;
import com.example.android.utils.LogUtility;
import com.google.firebase.crash.FirebaseCrash;

import io.fabric.sdk.android.Fabric;

public final class Reporter {
	public static void report(Throwable throwable) {
		LogUtility.e(throwable);

		if (BuildConfig.DEBUG) {
			return;
		}

		if (FirebaseCrash.isCrashCollectionEnabled()) {
			FirebaseCrash.report(throwable);
		}

		if (Fabric.isInitialized()) {
			Crashlytics.logException(throwable);
		}
	}
}
