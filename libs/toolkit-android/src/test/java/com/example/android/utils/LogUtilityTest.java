package com.example.android.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LogUtilityTest {
  @Test
  public void shouldNotFailWhenAnyLogMethodIsCalled() {
    LogUtility.d(new NullPointerException("Sample Null Pointer Exception"));
    LogUtility.d("Log", "Arg 1", "Arg 2");
    LogUtility.d(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");

    LogUtility.e(new NullPointerException("Sample Null Pointer Exception"));
    LogUtility.e("Log", "Arg 1", "Arg 2");
    LogUtility.e(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");

    LogUtility.w(new NullPointerException("Sample Null Pointer Exception"));
    LogUtility.w("Log", "Arg 1", "Arg 2");
    LogUtility.w(new NullPointerException("Sample Null Pointer Exception"), "Log", "Arg 1", "Arg 2");
  }
}
