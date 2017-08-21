package com.example.features.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AppContext;
import com.example.R;
import com.example.databinding.ProfileSummaryFragmentBinding;
import com.example.features.BaseFragment;
import com.example.features.authentication.LoginActivity;

public class ProfileSummaryFragment extends BaseFragment {
	ProfileSummaryFragmentBinding mBinding;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		mBinding = DataBindingUtil.inflate(inflater, R.layout.profile_summary_fragment, container, false);

		mBinding.signOut.setOnClickListener(view -> {
			AppContext.invalidate();
			startActivity(LoginActivity.startLogin(getContext()));
			getActivity().finish();
		});

		return mBinding.getRoot();
	}
}
