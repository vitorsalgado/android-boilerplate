package com.example.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public final class ActivityUtils {
	public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int frameId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		transaction.replace(frameId, fragment);
		transaction.commit();
	}

	public static void createPager(@NonNull Context context, @NonNull ImageView[] indicators, @NonNull ViewGroup container, @DrawableRes int selected, @DrawableRes int unselected) {
		container.removeAllViews();

		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new ImageView(context);
			indicators[i].setImageDrawable(ContextCompat.getDrawable(context, unselected));

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.leftMargin = 10;

			indicators[i].setLayoutParams(params);

			container.addView(indicators[i]);
		}

		indicators[0].setImageDrawable(ContextCompat.getDrawable(context, selected));
	}

	public static void markPage(@NonNull Context context, int currentPage, @NonNull ImageView[] indicators, @DrawableRes int selected, @DrawableRes int unselected) {
		int total = indicators.length;

		if (currentPage + 1 > total) {
			return;
		}

		for (ImageView indicator : indicators) {
			indicator.setImageDrawable(ContextCompat.getDrawable(context, unselected));
		}

		indicators[currentPage].setImageDrawable(ContextCompat.getDrawable(context, selected));
	}

	public static void makeStatusBarTranslucent(@NonNull Activity activity, @NonNull Toolbar toolbar) {
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		toolbar.setPadding(0, DimensionUtils.dpToPx(activity, 15), 0, 0);
	}


	@TargetApi(Build.VERSION_CODES.M)
	public static void requestPermissionsSafely(@NonNull Activity activity, @NonNull String[] permissions, int requestCode) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			ActivityCompat.requestPermissions(activity, permissions, requestCode);
		}
	}

	public static boolean hasPermission(@NonNull Context context, @NonNull String permission) {
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
				ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
	}

	public static boolean shouldRequestPermissionRationale(@NonNull Activity activity, @NonNull String permission) {
		return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
	}

	public static void openLink(@NonNull Context context, @NonNull String url) {
		context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}

	public static void shareText(@NonNull Context context, @NonNull String title, @NonNull String textToShare) {
		Intent sendIntent = new Intent();

		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
		sendIntent.setType("text/plain");

		context.startActivity(Intent.createChooser(sendIntent, title));
	}
}
