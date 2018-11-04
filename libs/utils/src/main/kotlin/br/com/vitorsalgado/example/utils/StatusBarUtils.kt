package br.com.vitorsalgado.example.utils

import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar

object StatusBarUtils {
  fun makeStatusBarTranslucent(activity: Activity, toolbar: Toolbar) {
    activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    toolbar.setPadding(0, DimensionUtils.dpToPx(activity, 15), 0, 0)
  }
}
