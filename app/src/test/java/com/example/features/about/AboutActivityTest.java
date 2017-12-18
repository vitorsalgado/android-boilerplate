package com.example.features.about;

import com.example.BaseRobotTest;
import com.example.TestApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import br.com.vitorsalgado.androidstarter.testutils.RxUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestApp.class)
public class AboutActivityTest extends BaseRobotTest {
	private AboutActivity activity;

	@Rule
	public final RxUtils.ImmediateSchedulersRule schedulers = new RxUtils.ImmediateSchedulersRule();

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
}
