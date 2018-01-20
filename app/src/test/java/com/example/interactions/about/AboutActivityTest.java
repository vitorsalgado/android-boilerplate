package com.example.interactions.about;

import android.app.Dialog;

import com.example.BuildConfig;
import com.example.TestApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowDialog;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = LOLLIPOP, application = TestApp.class, constants = BuildConfig.class)
@Ignore
public class AboutActivityTest {
	private AboutActivity activity;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(AboutActivity.class)
			.create()
			.resume()
			.get();

		assertThat(activity.isFinishing(), is(false));
	}

	@After
	public void tearDown() {
		activity.finish();
	}

	@Test
	public void shouldFinishOnBackPress() {
		activity.onBackPressed();
		assertThat(activity.isFinishing(), equalTo(true));
	}

	@Test
	public void shouldFinishOnHomeClick() {
		activity.onOptionsItemSelected(new RoboMenuItem(android.R.id.home));
		assertThat(activity.isFinishing(), equalTo(true));
	}

	@Test
	public void onLicensesClickShouldShowOpenSourceLicensesDialog() {
		activity.binding.viewLicenses.performClick();

		Dialog dialog = ShadowDialog.getLatestDialog();

		assertThat(dialog.isShowing(), equalTo(true));
		dialog.dismiss();
		assertThat(dialog.isShowing(), equalTo(false));
	}
}
