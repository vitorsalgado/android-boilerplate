package com.example.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SharedPreferencesHelper {

	private static final String PREF_FILE_NAME = "com.example.prefs";
	private final SharedPreferences mPref;

	public SharedPreferencesHelper(@NonNull Context context) {
		mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
	}

	public void clear() {
		mPref.edit().clear().apply();
	}

}
