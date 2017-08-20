package com.example.features;

import android.content.Context;
import android.support.compat.BuildConfig;
import android.support.v4.app.Fragment;

import com.example.utils.DevUtils;

public class BaseFragment extends Fragment {
	private BaseActivity mBaseActivity;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		if (context instanceof BaseActivity) {
			mBaseActivity = (BaseActivity) context;
		}
	}

	protected BaseActivity getBaseActivity() {
		return mBaseActivity;
	}

	@Override
	public void onDestroy() {
		if (BuildConfig.DEBUG) {
			DevUtils.watchMemoryLeaks(getActivity(), this);
		}

		super.onDestroy();
	}

	protected String tag() {
		return this.getClass().getSimpleName();
	}

	public final void showLoading() {
		if (this.mBaseActivity != null) {
			this.mBaseActivity.showLoading();
		}
	}

	public final void loaded() {
		if (this.mBaseActivity != null) {
			this.mBaseActivity.loaded();
		}
	}
}
