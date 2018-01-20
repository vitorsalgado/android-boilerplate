package com.example.interactions.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.example.R;
import com.example.android.utils.AppUtils;
import com.example.databinding.MainActivityBinding;
import com.example.interactions.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbstractActivity implements TabLayout.OnTabSelectedListener {
	private static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 9001;
	private static final int REQUEST_CODE_RECOVER_PLAY_SERVICES_WITHOUT_CALLBACK = 9002;
	private static final int REQUEST_CODE_WRITE_STORAGE_PERMISSION = 9003;

	MainActivityBinding binding;

	private int mCurrentTab = 0;
	private boolean mDoubleBackToExitPressedOnce = false;
	private Handler mDoubleBackHandler = new Handler();
	private Runnable mExitRunnable = (() -> mDoubleBackToExitPressedOnce = false);
	private Toast mExitToast;

	public static Intent newIntent(@NonNull Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		return intent;
	}

	//region Activity Events

	@NonNull
	@Override
	protected View root() {
		return binding.getRoot();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!AppUtils.checkPlayServices(this, REQUEST_CODE_RECOVER_PLAY_SERVICES)) {
			finish();
			return;
		}

		setLayout();
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
		mExitToast = Toast.makeText(this, R.string.double_back_to_exit, Toast.LENGTH_SHORT);

		mExitToast.show();
		mDoubleBackHandler.postDelayed(mExitRunnable, 2000);
	}

	@Override
	protected void onDestroy() {
		mDoubleBackHandler.removeCallbacks(mExitRunnable);
		super.onDestroy();
	}

	//endregion

	//region TabLayout Events

	@Override
	public void onTabSelected(TabLayout.Tab tab) {
		binding.tabPager.setCurrentItem(tab.getPosition());
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

	//endregion

	//region Helpers

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
		setTheme(R.style.AppTheme);
		binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

		List<Fragment> fragments = new ArrayList<>();
		// Here we add the fragments for tabbed navigation
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
		binding.tabPager.setAdapter(viewPagerAdapter);

		// Offscreen limit according to total tabs
		// binding.tabPager.setOffscreenPageLimit(<TOTAL_TABS>);

		binding.tablayoutMain.setupWithViewPager(binding.tabPager);
		binding.tablayoutMain.setTabGravity(TabLayout.GRAVITY_FILL);
		binding.tablayoutMain.addOnTabSelectedListener(this);

		binding.tabPager.setCurrentItem(mCurrentTab);

		// Adding tab icons
		// setTabIcon(binding.tablayoutMain.getTabAt(0), <ICON_RESOURCE>);

		// Initializing tabs states
		// setTabColor(binding.tablayoutMain.getTabAt(0), ContextCompat.getColor(this, R.color.tab_unselected));
		// setTabColor(binding.tablayoutMain.getTabAt(mCurrentTab), ContextCompat.getColor(this, R.color.tab_seletected));
	}
	
	//endregion
}
