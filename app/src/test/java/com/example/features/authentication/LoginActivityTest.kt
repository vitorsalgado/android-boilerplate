package com.example.features.authentication

import android.view.View
import android.widget.Button
import com.example.BuildConfig
import com.example.R
import com.example.TestApp
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
class LoginActivityTest {

	@Test
	fun itShouldShowFacebookLogin() {
		val loginActivity = Robolectric.setupActivity(LoginActivity::class.java)
		val facebookLogin = loginActivity.findViewById<Button>(R.id.facebookLogin)

		assert(facebookLogin.visibility == View.VISIBLE)
	}

}
