package com.example.analytics;

import com.google.firebase.analytics.FirebaseAnalytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AnalyticsUtils {
  public static void trackAction(@NonNull Context context, @NonNull @Action String action) {
    trackAction(context, action, null);
  }

  public static void trackAction(@NonNull Context context, @NonNull @Action String action, @Nullable Bundle extras) {
    FirebaseAnalytics.getInstance(context).logEvent(action, extras);
  }

  public static void trackView(@NonNull Activity activity, @NonNull @Screen String screen) {
    FirebaseAnalytics.getInstance(activity).setCurrentScreen(activity, screen, null);
  }
}
