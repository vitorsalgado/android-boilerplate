package com.example.android.utils

import android.app.Activity
import android.support.v7.widget.Toolbar
import android.view.WindowManager

object StatusBarUtils {
    fun makeStatusBarTranslucent(activity: Activity, toolbar: Toolbar) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        toolbar.setPadding(0, DimensionUtils.dpToPx(activity, 15), 0, 0)
    }
}
