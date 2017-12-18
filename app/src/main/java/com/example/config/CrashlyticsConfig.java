package com.example.config;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.BuildConfig;

import io.fabric.sdk.android.Fabric;

public final class CrashlyticsConfig {
	public static void setup(@NonNull Context context) {
		if (BuildConfig.DEBUG) {
			return;
		}

		Fabric.with(context, new com.crashlytics.android.Crashlytics());
	}
}
