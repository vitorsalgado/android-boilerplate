package com.example.features.about;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.utils.AppUtils;
import com.example.features.about.databinding.AboutActivityBinding;

import de.psdev.licensesdialog.LicensesDialog;

public class AboutActivity extends AppCompatActivity {
	AboutActivityBinding binding;

	@NonNull
	public static Intent newIntent(@NonNull Context context) {
		return new Intent(context, AboutActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.about_activity);

		// setUpToolBar(binding.toolbar, getString(R.string.about));

		binding.appVersion.setText(AppUtils.getVersionName(getApplicationContext()));

		binding.viewLicenses.setOnClickListener(view ->
			new LicensesDialog.Builder(this)
				.setNotices(R.raw.notices)
				.setIncludeOwnLicense(true)
				.setTitle(R.string.licenses)
				.setCloseText(R.string.close)
				.build()
				.show());
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
