package com.example.features;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.features.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainTest {
  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void ensureHomeInitialization() {
    assertFalse(mActivityTestRule.getActivity().isFinishing());
    mActivityTestRule.finishActivity();
  }
}
