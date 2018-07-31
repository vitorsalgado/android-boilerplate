package br.com.vitorsalgado.example.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtils {
  @TargetApi(Build.VERSION_CODES.M)
  fun requestPermissionsSafely(activity: Activity, permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
  }

  fun hasPermission(context: Context, permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
  }

  fun shouldRequestPermissionRationale(activity: Activity, permission: String): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
  }
}
