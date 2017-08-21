package com.example

import com.facebook.FacebookSdk
import com.squareup.leakcanary.RefWatcher
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

class TestApp : App(), TestLifecycleApplication {
	override fun onCreate() {
		super.onCreate()

		FacebookSdk.sdkInitialize(this);
	}

	override fun setUpLeakCanary(): RefWatcher {
		return RefWatcher.DISABLED
	}

	override fun setUpCrashlytics() {

	}

	override fun beforeTest(method: Method?) {

	}

	override fun prepareTest(test: Any?) {
	}

	override fun afterTest(method: Method?) {
	}

}