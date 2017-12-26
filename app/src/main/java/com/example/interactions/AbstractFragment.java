package com.example.interactions;

import android.support.compat.BuildConfig;
import android.support.v4.app.Fragment;

import com.example.App;
import com.squareup.leakcanary.RefWatcher;

public class AbstractFragment extends Fragment {
	@Override
	public void onDestroy() {
		if (BuildConfig.DEBUG) {
			RefWatcher refWatcher = App.getRefWatcher();
			refWatcher.watch(this);
		}

		super.onDestroy();
	}
}
