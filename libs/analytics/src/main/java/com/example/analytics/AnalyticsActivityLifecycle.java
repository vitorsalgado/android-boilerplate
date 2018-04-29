package com.example.analytics;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class AnalyticsActivityLifecycle implements Application.ActivityLifecycleCallbacks {
  @Override
  public void onActivityCreated(Activity activity, Bundle bundle) {

  }

  @Override
  public void onActivityStarted(Activity activity) {

  }

  @Override
  public void onActivityResumed(Activity activity) {
    if (activity instanceof TraceableScreen) {
      AnalyticsUtils.trackView(activity, ((TraceableScreen) activity).getScreenName());
    }
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
