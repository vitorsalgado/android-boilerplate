package com.example.features.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.R;
import com.example.databinding.MainActivityBinding;
import com.example.features.BaseActivity;
import com.example.features.authentication.LoginActivity;
import com.example.utils.AppUtils;

import java.util.Vector;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, MainView {
	static final String CURRENT_TAB = "com.example.features.MainActivity.CURRENT_TAB";
	static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 1;

	MainActivityBinding mBinding;
	MainPresenter mPresenter;
	ViewPagerAdapter mViewPagerAdapter;
	int mCurrentTab = 0;
	boolean mDoubleBackToExitPressedOnce = false;
	Handler mDoubleBackHandler = new Handler();
	Runnable mExitRunnable = (() -> mDoubleBackToExitPressedOnce = false);
	Toast mExitToast;

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);

		mPresenter = new MainPresenter(this);

		if (AppUtils.checkPlayServices(this, REQUEST_CODE_RECOVER_PLAY_SERVICES)) {
			mPresenter.checkAuthentication();
		}

		mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
	}

	@Override
	public void onBackPressed() {
		if (mDoubleBackToExitPressedOnce) {
			super.onBackPressed();

			if (mExitToast != null) {
				mExitToast.cancel();
			}

			finish();
			return;
		}

		mDoubleBackToExitPressedOnce = true;
		mExitToast = Toast.makeText(this, R.string.common_double_back_to_exit, Toast.LENGTH_SHORT);

		mExitToast.show();
		mDoubleBackHandler.postDelayed(mExitRunnable, 2000);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(CURRENT_TAB, mCurrentTab);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mCurrentTab = savedInstanceState == null ? 0 : savedInstanceState.getInt(CURRENT_TAB);
	}

	@Override
	protected void onDestroy() {
		mDoubleBackHandler.removeCallbacks(mExitRunnable);
		super.onDestroy();
	}

	// MainView implementations

	@Override
	public void onAuthCheckResult(boolean isAuth) {
		if (!isAuth) {
			startActivity(LoginActivity.startLogin(this));
			finish();
			return;
		}

		setLayout();
	}

	// TabLayout event handlers

	@Override
	public void onTabSelected(TabLayout.Tab tab) {
		mBinding.tabPager.setCurrentItem(tab.getPosition());
		mCurrentTab = tab.getPosition();

		setTabColor(tab, ContextCompat.getColor(this, R.color.tab_seletected));
	}

	@Override
	public void onTabUnselected(TabLayout.Tab tab) {
		setTabColor(tab, ContextCompat.getColor(this, R.color.tab_seletected));
	}

	@Override
	public void onTabReselected(TabLayout.Tab tab) {

	}

	// Helper methods

	private void setTabIcon(TabLayout.Tab tab, @DrawableRes int icon) {
		if (tab == null) {
			return;
		}

		tab.setIcon(icon);
	}

	private void setTabColor(TabLayout.Tab tab, int color) {
		if (tab == null || tab.getIcon() == null) {
			return;
		}

		tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}

	private void setLayout() {
		setContentView(R.layout.main_activity);

		Vector<Fragment> fragments = new Vector<>();

		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);

		mBinding.tabPager.setAdapter(mViewPagerAdapter);
		mBinding.tabPager.setOffscreenPageLimit(3);

		mBinding.tablayoutMain.setupWithViewPager(mBinding.tabPager);
		mBinding.tablayoutMain.setTabGravity(TabLayout.GRAVITY_FILL);
		mBinding.tablayoutMain.addOnTabSelectedListener(this);
	}
}
