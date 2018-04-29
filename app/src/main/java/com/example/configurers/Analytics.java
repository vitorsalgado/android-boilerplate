package com.example.configurers;

import com.google.firebase.analytics.FirebaseAnalytics;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.BuildConfig;
import com.example.analytics.AnalyticsActivityLifecycle;

public final class Analytics {
  public static void setup(@NonNull Application application) {
    application.registerActivityLifecycleCallbacks(new AnalyticsActivityLifecycle());
    FirebaseAnalytics.getInstance(application).setAnalyticsCollectionEnabled(!BuildConfig.DEBUG);
  }
}
