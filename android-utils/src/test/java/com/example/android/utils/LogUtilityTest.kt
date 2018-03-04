package com.example.android.utils

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LogUtilityTest {
	@Test
	fun shouldNotFailWhenAnyLogMethodIsCalled() {
		LogUtility.d(NullPointerException("Sample Null Pointer Exception"))
		LogUtility.d("Log", "Arg 1", "Arg 2")
		LogUtility.d(NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2")

		LogUtility.e(NullPointerException("Sample Null Pointer Exception"))
		LogUtility.e("Log", "Arg 1", "Arg 2")
		LogUtility.e(NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2")

		LogUtility.w(NullPointerException("Sample Null Pointer Exception"))
		LogUtility.w("Log", "Arg 1", "Arg 2")
		LogUtility.w(NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2")
	}
}
