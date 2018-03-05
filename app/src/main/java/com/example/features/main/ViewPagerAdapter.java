package com.example.features.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

class ViewPagerAdapter extends FragmentStatePagerAdapter {
	private final List<Fragment> mFragmentList;

	ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragments) {
		super(fm);
		mFragmentList = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}
}
