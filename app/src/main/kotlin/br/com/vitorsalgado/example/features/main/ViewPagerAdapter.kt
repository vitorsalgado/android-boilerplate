package br.com.vitorsalgado.example.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class ViewPagerAdapter(fm: FragmentManager, private val mFragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
    return mFragmentList[position]
  }

  override fun getCount(): Int {
    return mFragmentList.size
  }
}
