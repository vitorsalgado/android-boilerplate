package com.example.trackers

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.example.App

import com.example.android.utils.LogUtility

class FragmentLifecycleTracker : FragmentManager.FragmentLifecycleCallbacks() {
  override fun onFragmentActivityCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
    LogUtility.d("Fragment CREATED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentViewCreated(fm: FragmentManager?, f: Fragment?, v: View?, savedInstanceState: Bundle?) {
    LogUtility.d("Fragment VIEW CREATED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentStarted(fm: FragmentManager?, f: Fragment?) {
    LogUtility.d("Fragment STARTED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
    LogUtility.d("Fragment ATTACHED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
    LogUtility.d("Fragment RESUMED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentSaveInstanceState(fm: FragmentManager?, f: Fragment?, outState: Bundle?) {
    LogUtility.d("Fragment SAVED INSTANCE STATE --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentDestroyed(fm: FragmentManager?, f: Fragment?) {
    LogUtility.d("Fragment DESTROYED --> " + f!!.javaClass.simpleName)
    App.refWatcher.watch(this)
  }

  override fun onFragmentPaused(fm: FragmentManager?, f: Fragment?) {
    LogUtility.d("Fragment PAUSED --> " + f!!.javaClass.simpleName)
  }

  override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
    LogUtility.d("Fragment VIEW DESTROYED --> " + f!!.javaClass.simpleName)
  }
}
