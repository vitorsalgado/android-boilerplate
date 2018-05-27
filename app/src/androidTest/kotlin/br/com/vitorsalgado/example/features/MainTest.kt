package br.com.vitorsalgado.example.features

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.vitorsalgado.example.features.main.MainActivity
import junit.framework.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainTest {
  @Rule
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun ensureHomeInitialization() {
    assertFalse(mActivityTestRule.activity.isFinishing)
    mActivityTestRule.finishActivity()
  }
}
