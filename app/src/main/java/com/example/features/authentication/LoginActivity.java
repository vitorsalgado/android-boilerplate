package com.example.features.authentication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.AppDeps;
import com.example.R;
import com.example.databinding.LoginActivityBinding;
import com.example.features.BaseActivity;
import com.example.features.main.MainActivity;
import com.example.utils.ActivityUtils;
import com.example.utils.DialogUtils;

public class LoginActivity extends BaseActivity implements LoginView {
	private static final String TAG = "LoginActivity";
	private static final String EXTRA_ERROR_MESSAGE = "com.example.LoginActivity.ERROR_MESSAGE";

	private LoginPresenter mLoginPresenter;
	private LoginActivityBinding mBinding;
	private ImageView[] mPagination = new ImageView[3];

	public static Intent startLogin(@NonNull Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		return intent;
	}

	public static Intent startLoginWithError(@NonNull Context context, @NonNull String error) {
		Intent intent = startLogin(context);
		intent.putExtra(EXTRA_ERROR_MESSAGE, error);

		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);
		mLoginPresenter = new LoginPresenter(this, AppDeps.authenticationManager(), AppDeps.fbCallbackManager());

		mLoginPresenter.start();
		mBinding.facebookLogin.setOnClickListener(view -> mLoginPresenter.loginWithFacebook(this));
		loaded();
		ActivityUtils.createPager(this, mPagination, mBinding.pageIndicators, R.drawable.ic_selected_page_accent, R.drawable.ic_unselected_page_accent);

		String mErrorMessage = getIntent().getStringExtra(EXTRA_ERROR_MESSAGE);

		if (mErrorMessage != null) {
			mLoginPresenter.logout();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mLoginPresenter.result(requestCode, resultCode, data);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onDestroy() {
		mLoginPresenter.unsubscribe();
		super.onDestroy();
	}

	// LoginView implementation

	@Override
	public void onLoginSuccess() {
		loaded();
		startActivity(MainActivity.newIntent(this));
	}

	@Override
	public void onLoginError(Throwable ex) {
		Log.e(TAG, ex.getMessage(), ex);

		loaded();
		DialogUtils.simpleOk(this, R.string.login_error_unexpected_title, R.string.login_error_unexpected_description).show();
	}

	@Override
	public void onLogout() {

	}
}
