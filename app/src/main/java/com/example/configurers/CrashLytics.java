package com.example.configurers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.example.BuildConfig;

import io.fabric.sdk.android.Fabric;

public final class CrashLytics {
	public static void setup(@NonNull Context context) {
		if (BuildConfig.DEBUG) {
			return;
		}

		Fabric.with(context, new Crashlytics());
	}
}
