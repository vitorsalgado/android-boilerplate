package com.example.trackers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.utils.LogUtility;

public class ActivityLifecycleTracker implements Application.ActivityLifecycleCallbacks {
	private final FragmentLifecycleTracker tracker;

	public ActivityLifecycleTracker(FragmentLifecycleTracker tracker) {
		this.tracker = tracker;
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		LogUtility.d("Activity CREATED --> " + activity.getClass().getSimpleName());

		if (activity instanceof AppCompatActivity) {
			((AppCompatActivity) activity).getSupportFragmentManager()
				.registerFragmentLifecycleCallbacks(tracker, false);
		}
	}

	@Override
	public void onActivityStarted(Activity activity) {
		LogUtility.d("Activity STARTED --> " + activity.getClass().getSimpleName());
	}

	@Override
	public void onActivityResumed(Activity activity) {
		LogUtility.d("Activity RESUMED --> " + activity.getClass().getSimpleName());
	}

	@Override
	public void onActivityPaused(Activity activity) {
		LogUtility.d("Activity PAUSED --> " + activity.getClass().getSimpleName());
	}

	@Override
	public void onActivityStopped(Activity activity) {
		LogUtility.d("Activity STOPPED --> " + activity.getClass().getSimpleName());
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
		LogUtility.d("Activity SAVED INSTANCE STATE --> " + activity.getClass().getSimpleName());
	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		LogUtility.d("Activity DESTROYED --> " + activity.getClass().getSimpleName());

		if (activity instanceof AppCompatActivity) {
			((AppCompatActivity) activity).getSupportFragmentManager()
				.unregisterFragmentLifecycleCallbacks(tracker);
		}
	}
}
