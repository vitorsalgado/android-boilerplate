package com.example.features.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

internal class ViewPagerAdapter(fm: FragmentManager, private val mFragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
    return mFragmentList[position]
  }

  override fun getCount(): Int {
    return mFragmentList.size
  }
}
