package com.example;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

public class Utils {
  public static Activity getCurrentActivity() {
    final Activity[] currentActivity = new Activity[1];

    onView(allOf(withId(android.R.id.content), isDisplayed())).perform(new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return isAssignableFrom(View.class);
      }

      @Override
      public String getDescription() {
        return "getting text from a TextView";
      }

      @Override
      public void perform(UiController uiController, View view) {
        Activity activity1 = ((Activity) view.getContext());
        currentActivity[0] = activity1;
      }
    });

    return currentActivity[0];
  }
}
