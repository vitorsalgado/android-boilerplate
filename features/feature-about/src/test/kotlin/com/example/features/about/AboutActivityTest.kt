package com.example.features.about

import kotlinx.android.synthetic.main.about_activity.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.fakes.RoboMenuItem
import org.robolectric.shadows.ShadowDialog


@RunWith(RobolectricTestRunner::class)
@Ignore
class AboutActivityTest {
  private var activity: AboutActivity? = null

  @Before
  fun setUp() {
    activity = Robolectric.buildActivity(AboutActivity::class.java)
      .create()
      .resume()
      .get()

    assertThat(activity!!.isFinishing, `is`(false))
  }

  @After
  fun tearDown() {
    activity!!.finish()
  }

  @Test
  fun shouldFinishOnBackPress() {
    activity!!.onBackPressed()
    assertThat(activity!!.isFinishing, equalTo(true))
  }

  @Test
  fun shouldFinishOnHomeClick() {
    activity!!.onOptionsItemSelected(RoboMenuItem(android.R.id.home))
    assertThat(activity!!.isFinishing, equalTo(true))
  }

  @Test
  fun onLicensesClickShouldShowOpenSourceLicensesDialog() {
    activity!!.viewLicenses.performClick()

    val dialog = ShadowDialog.getLatestDialog()

    assertThat(dialog.isShowing, equalTo(true))
    dialog.dismiss()
    assertThat(dialog.isShowing, equalTo(false))
  }
}
