package com.example;

import com.crashlytics.android.Crashlytics;
import com.example.android.utils.LogUtility;

import io.fabric.sdk.android.Fabric;

public final class Reporter {
	public static void report(Throwable throwable) {
		LogUtility.e(throwable);

		if (BuildConfig.DEBUG) {
			return;
		}

		if (Fabric.isInitialized()) {
			Crashlytics.logException(throwable);
		}
	}
}
