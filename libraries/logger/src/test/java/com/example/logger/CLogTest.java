package com.example.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class CLogTest {
	@Test
	public void shouldNotFailWhenAnyLogMethodIsCalled() {
		CLog.d(new NullPointerException("Sample Null Pointer Exception"));
		CLog.d("Log", "Arg 1", "Arg 2");
		CLog.d(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");

		CLog.e(new NullPointerException("Sample Null Pointer Exception"));
		CLog.e("Log", "Arg 1", "Arg 2");
		CLog.e(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");

		CLog.w(new NullPointerException("Sample Null Pointer Exception"));
		CLog.w("Log", "Arg 1", "Arg 2");
		CLog.w(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");
	}
}
