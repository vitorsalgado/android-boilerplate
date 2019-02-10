package br.com.vitorsalgado.example.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

object AppUtils {
  fun checkPlayServices(activity: Activity, resolutionCode: Int): Boolean {
    val googleAPI = GoogleApiAvailability.getInstance()

    val result = googleAPI.isGooglePlayServicesAvailable(activity)

    if (result != ConnectionResult.SUCCESS) {
      if (googleAPI.isUserResolvableError(result)) {
        googleAPI.getErrorDialog(activity, result, resolutionCode).show()
      }

      return false
    }

    return true
  }

  fun isLocationServiceEnabled(context: Context): Boolean {
    val gpsEnabled: Boolean
    val networkEnabled: Boolean

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    try {
      gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
      networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (ex: Exception) {
      return false
    }

    return gpsEnabled || networkEnabled
  }

  fun hasNetworkConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetworkInfo

    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
  }

  fun getVersionName(context: Context): String {
    val manager = context.packageManager

    try {
      return manager.getPackageInfo(context.packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
      throw RuntimeException(e)
    }
  }
}
