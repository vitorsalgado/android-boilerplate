package com.example.features.authentication

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class LoginActivityTest {

    @Test
    fun itShould() {
        val loginActivity = Robolectric.buildActivity(LoginActivity::class.java)
    }

}
