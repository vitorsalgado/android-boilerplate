package com.example.features;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.BuildConfig;
import com.example.R;
import com.example.utils.ActivityUtils;
import com.example.utils.DialogUtils;
import com.example.utils.DimensionUtils;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public abstract class BaseActivity extends AppCompatActivity {
	@Nullable View mErrorActionHolder;
	@Nullable Button mBtnErrorAction;
	@Nullable TextView mTextViewErrorAction;

	public static final SpringConfig SPRING_CONFIG = SpringConfig.fromOrigamiTensionAndFriction(50, 2);
	protected final BaseSpringSystem mSpringSystem = SpringSystem.create();

	private Dialog mProgressDialog;
	private boolean mIsErrorComponentAvailable;

	// BaseActivity event handlers

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mErrorActionHolder = findViewById(R.id.error_action_component_holder);
		mBtnErrorAction = findViewById(R.id.error_action_button);
		mTextViewErrorAction = findViewById(R.id.error_action_text);

		mIsErrorComponentAvailable = mErrorActionHolder != null && mBtnErrorAction != null && mTextViewErrorAction != null;
	}

	@Override
	protected void onDestroy() {
		DialogUtils.dismiss(mProgressDialog);
		mSpringSystem.removeAllListeners();

		super.onDestroy();
	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		if (BuildConfig.WATERMARK) {
			final ViewGroup viewGroupParent = this.findViewById(android.R.id.content);
			final ViewGroup viewGroup = (ViewGroup) viewGroupParent.getChildAt(0);

			ActivityUtils.addWatermark(this, viewGroup, viewGroupParent);
		}
	}

	// Toolbars

	protected void setUpToolBar(@NonNull Toolbar toolbar, @StringRes int title) {
		setSupportActionBar(toolbar);

		final ActionBar supportActionBar = getSupportActionBar();

		if (supportActionBar == null) {
			throw new IllegalStateException("failed to setup supportActionBar. supportActionBar is null.");
		}

		supportActionBar.setDisplayHomeAsUpEnabled(true);
		supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

		if (title != 0) {
			supportActionBar.setTitle(title);
		}
	}

	protected void makeStatusBarTranslucent(@NonNull Toolbar toolbar) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			toolbar.setPadding(0, DimensionUtils.dpToPx(this, 15), 0, 0);
		}
	}

	// Default Presenter View Methods

	public void showLoading() {
		mProgressDialog = DialogUtils.loading(this);
		mProgressDialog.show();
	}

	public void loaded() {
		DialogUtils.dismiss(mProgressDialog);
	}

	// Spring Animations

	public BaseSpringSystem getSpringSystem() {
		return mSpringSystem;
	}

	public static class ViewSpringListener extends SimpleSpringListener {
		View mView;

		public ViewSpringListener(@NonNull View view) {
			mView = view;
		}

		@Override
		public void onSpringUpdate(Spring spring) {
			float value = (float) spring.getCurrentValue();
			float scale = 1f - (value * 0.5f);

			mView.setScaleX(scale);
			mView.setScaleY(scale);
		}
	}

	// General

	protected String getTag() {
		return this.getClass().getSimpleName();
	}
}
