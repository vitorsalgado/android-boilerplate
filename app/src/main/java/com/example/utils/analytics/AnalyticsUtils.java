package com.example.utils.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.analytics.FirebaseAnalytics;

import static com.example.utils.Preconditions.checkNotNull;

public class AnalyticsUtils {
	public static void trackAction(@NonNull Context context, @NonNull @Action String action) {
		trackAction(context, action, null);
	}

	public static void trackAction(@NonNull Context context, @NonNull @Action String action, @Nullable Bundle extras) {
		checkNotNull(context);
		checkNotNull(action);

		FirebaseAnalytics.getInstance(context).logEvent(action, extras);
	}

	public static void trackView(@NonNull Activity activity, @NonNull @Screen String screen) {
		checkNotNull(activity);
		checkNotNull(screen);

		FirebaseAnalytics.getInstance(activity).setCurrentScreen(activity, screen, null);
	}
}
