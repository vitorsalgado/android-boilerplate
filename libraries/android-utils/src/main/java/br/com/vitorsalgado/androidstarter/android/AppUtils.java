package br.com.vitorsalgado.androidstarter.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import br.com.vitorsalgado.androidstarter.logger.CLog;

public final class AppUtils {
	public static boolean checkPlayServices(@NonNull Activity activity, int resolutionCode) {
		GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();

		int result = googleAPI.isGooglePlayServicesAvailable(activity);

		if (result != ConnectionResult.SUCCESS) {
			if (googleAPI.isUserResolvableError(result)) {
				googleAPI.getErrorDialog(activity, result, resolutionCode).show();
			}

			return false;
		}

		return true;
	}

	public static boolean isLocationServiceEnabled(@NonNull Context context) {
		boolean gpsEnabled;
		boolean networkEnabled;

		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		if (locationManager == null) {
			return false;
		}

		try {
			gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
			return false;
		}

		return gpsEnabled || networkEnabled;
	}

	public static boolean hasNetworkConnection(@NonNull Context context) {
		ConnectivityManager connectivityManager =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) {
			return false;
		}

		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

		return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
	}

	public static String getVersionName(@NonNull Context context) {
		PackageManager manager = context.getPackageManager();

		try {
			return manager.getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			CLog.e(e);
			return "";
		}
	}
}
