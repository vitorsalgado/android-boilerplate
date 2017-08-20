package com.example.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class FormUtils {
	@NonNull
	@SuppressLint("all")
	public static Spanned fromHtml(@NonNull String html) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
		} else {
			return Html.fromHtml(html);
		}
	}

	public static boolean isEmpty(@NonNull EditText editText, @NonNull String error) {
		if (editText.getText().toString().isEmpty()) {
			editText.setError(error);

			return true;
		}

		return false;
	}

	public static String parseDate(@NonNull Date date, @NonNull String format) {
		return new SimpleDateFormat(format, Locale.getDefault()).format(date);
	}

	public static String parseTime(@NonNull Date date, @NonNull String format) {
		return new SimpleDateFormat(format, Locale.getDefault()).format(date);
	}

	public static Date parseDateTime(@NonNull String dateStr, @NonNull String timeStr, @NonNull String format) {
		DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
		String strDate = String.format("%s %s", dateStr, timeStr);

		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
