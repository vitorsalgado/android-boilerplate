package br.com.vitorsalgado.androidstarter.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
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
