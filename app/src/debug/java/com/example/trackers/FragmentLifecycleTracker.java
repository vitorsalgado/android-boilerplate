package com.example.trackers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.android.utils.LogUtility;

public class FragmentLifecycleTracker extends FragmentManager.FragmentLifecycleCallbacks {
  @Override
  public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
    LogUtility.d("Fragment CREATED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
    LogUtility.d("Fragment VIEW CREATED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentStarted(FragmentManager fm, Fragment f) {
    LogUtility.d("Fragment STARTED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
    LogUtility.d("Fragment ATTACHED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentResumed(FragmentManager fm, Fragment f) {
    LogUtility.d("Fragment RESUMED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
    LogUtility.d("Fragment SAVED INSTANCE STATE --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
    LogUtility.d("Fragment DESTROYED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentPaused(FragmentManager fm, Fragment f) {
    LogUtility.d("Fragment PAUSED --> " + f.getClass().getSimpleName());
  }

  @Override
  public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
    LogUtility.d("Fragment VIEW DESTROYED --> " + f.getClass().getSimpleName());
  }
}
