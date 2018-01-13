package com.example.android.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public final class DimensionUtils {
	public static int dpToPx(@NonNull Context context, int dp) {
		return Math.round(dp * getPixelScaleFactor(context));
	}

	public static int pxToDp(@NonNull Context context, int px) {
		return Math.round(px / getPixelScaleFactor(context));
	}

	private static float getPixelScaleFactor(@NonNull Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
	}
}
