package com.example.utils;

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
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.BuildConfig;
import com.example.R;

public class ActivityUtils {
	public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int frameId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		transaction.replace(frameId, fragment);
		transaction.commit();
	}

	public static void replaceWithSlideAnimation(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int frameId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
		transaction.replace(frameId, fragment);
		transaction.addToBackStack(fragment.getClass().getName());

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

	@TargetApi(Build.VERSION_CODES.M)
	public static void requestPermissionsSafely(@NonNull Activity activity, String[] permissions, int requestCode) {
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

	public static void shareText(@NonNull Context context, @NonNull String textToShare) {
		Intent sendIntent = new Intent();

		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
		sendIntent.setType("text/plain");

		context.startActivity(Intent.createChooser(sendIntent,
				context.getResources().getText(R.string.app_share)));
	}

	public static void addWatermark(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroupParent) {
		TextView text = new TextView(context);
		text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		text.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.action_blue, null));
		text.setText(context.getString(R.string.version_with_code, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		ViewCompat.setZ(text, 10);

		viewGroupParent.removeAllViews();

		FrameLayout frame = new FrameLayout(context);

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

		frame.addView(text, params);
		frame.addView(viewGroup);

		viewGroupParent.addView(frame);
	}
}
