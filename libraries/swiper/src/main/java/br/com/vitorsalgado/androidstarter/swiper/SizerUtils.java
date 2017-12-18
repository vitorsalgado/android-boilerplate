package br.com.vitorsalgado.androidstarter.swiper;

import android.content.Context;
import android.util.DisplayMetrics;

final class SizerUtils {
	static int dpToPx(Context context, int dp) {
		return Math.round(dp * getPixelScaleFactor(context));
	}

	private static float getPixelScaleFactor(Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
	}
}
