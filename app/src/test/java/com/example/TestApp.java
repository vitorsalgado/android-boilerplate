package com.example;

import com.facebook.soloader.SoLoader;
import com.squareup.leakcanary.RefWatcher;

public class TestApp extends DebugApp {
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected RefWatcher enableLeakCanary() {
		return RefWatcher.DISABLED;
	}
}
