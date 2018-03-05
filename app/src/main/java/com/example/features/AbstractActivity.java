package com.example.features;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.R;
import com.example.Reporter;
import com.example.android.utils.AppUtils;
import com.example.android.utils.DialogUtils;
import com.example.toolkit.RetryCallback;

public abstract class AbstractActivity extends AppCompatActivity {
	@Nullable
	private Dialog progressDialog;

	@NonNull
	protected abstract View root();

	//region Activity Events

	@Override
	protected void onResume() {
		if (!AppUtils.hasNetworkConnection(getApplicationContext())) {
			Snackbar.make(root(), R.string.error_no_internet_connection, Snackbar.LENGTH_LONG).show();
		}

		super.onResume();
	}

	@Override
	public void onBackPressed() {
		DialogUtils.dismiss(progressDialog);
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		DialogUtils.dismiss(progressDialog);
		super.onDestroy();
	}

	//endregion

	//region Common Components Setup

	protected void setUpToolBar(@NonNull Toolbar toolBar) {
		setUpToolBar(toolBar, "");
	}

	protected void setUpToolBar(@NonNull Toolbar toolBar, @NonNull String title) {
		setSupportActionBar(toolBar);

		final ActionBar supportActionBar = getSupportActionBar();

		if (supportActionBar == null) {
			throw new IllegalStateException("failed to setup supportActionBar. supportActionBar is null.");
		}

		supportActionBar.setDisplayHomeAsUpEnabled(true);
		supportActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back));
		supportActionBar.setTitle(title);
	}

	@StringRes
	protected int getUnexpectedErrorMessage() {
		return 0;
	}

	//endregion

	//region Common Presenter View Events

	public void showLoading() {
		progressDialog = DialogUtils.loading(this, R.style.TransparentProgressDialog, R.color.accent);
		progressDialog.show();
	}

	public void loaded() {
		DialogUtils.dismiss(progressDialog);
	}

	public void error(Throwable throwable) {
		Reporter.report(throwable);

		DialogUtils.dismiss(progressDialog);
		DialogUtils.simpleOk(this, R.string.error, getUnexpectedErrorMessage());
	}

	public void errorWithRetry(Throwable throwable, RetryCallback retryAction) {
		Reporter.report(throwable);

		DialogUtils.dismiss(progressDialog);

		DialogUtils.retry(this,
			getUnexpectedErrorMessage(),
			(dialog, which) -> {
				retryAction.retry();
				dialog.dismiss();
			},
			(dialog, which) -> dialog.dismiss())
			.show();
	}

	//endregion
}
