package com.example.utils;

import android.content.Context;

import com.example.App;
import com.squareup.leakcanary.RefWatcher;

public class DevUtils {

	public static void watchMemoryLeaks(Context context, Object fragment) {
		RefWatcher refWatcher = App.getRefWatcher(context);
		refWatcher.watch(fragment);
	}

	public static void printHashKey(Context context) {

	}

}
