package com.example.interactions.about;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.example.R;
import com.example.databinding.AboutActivityBinding;
import com.example.interactions.AbstractActivity;

import br.com.vitorsalgado.androidstarter.android.AppUtils;

public class AboutActivity extends AbstractActivity {
	AboutActivityBinding binding;

	@NonNull
	public static Intent newIntent(@NonNull Context context) {
		return new Intent(context, AboutActivity.class);
	}

	@NonNull
	@Override
	protected View root() {
		return binding.getRoot();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.about_activity);

		setUpToolBar(binding.toolbar, getString(R.string.about));

		binding.appVersion.setText(AppUtils.getVersionName(getApplicationContext()));
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
