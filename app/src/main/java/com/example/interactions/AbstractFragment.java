package com.example.interactions;

import android.support.compat.BuildConfig;
import android.support.v4.app.Fragment;

import com.example.App;
import com.squareup.leakcanary.RefWatcher;

import com.example.toolkit.interfaces.RetryCallback;

public class AbstractFragment extends Fragment implements PresenterView {
	@Override
	public void onDestroy() {
		if (BuildConfig.DEBUG) {
			RefWatcher refWatcher = App.getRefWatcher();
			refWatcher.watch(this);
		}

		super.onDestroy();
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void loaded() {

	}

	@Override
	public void error(Throwable throwable) {

	}

	@Override
	public void errorWithRetry(Throwable throwable, RetryCallback retryAction) {

	}
}
