package br.com.vitorsalgado.example

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

object Utils {
  val currentActivity: Activity?
    get() {
      val currentActivity = arrayOfNulls<Activity>(1)

      onView(allOf(withId(android.R.id.content), isDisplayed())).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
          return isAssignableFrom(View::class.java)
        }

        override fun getDescription(): String {
          return "getting text from a TextView"
        }

        override fun perform(uiController: UiController, view: View) {
          val activity1 = view.context as Activity
          currentActivity[0] = activity1
        }
      })

      return currentActivity[0]
    }
}
