package com.example.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Locale;

public class AppUtils {
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

        try {
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            return false;
        }

        return gpsEnabled || networkEnabled;
    }

    public static Maybe<Location> getLastKnownLocation(@NonNull Context context) throws SecurityException {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Location lastKnownNetworkLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location lastKnownGPSLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Location bestLocation;

        if (lastKnownGPSLocation != null) {
            bestLocation = lastKnownGPSLocation;
        } else if (lastKnownNetworkLocation != null) {
            bestLocation = lastKnownNetworkLocation;
        } else {
            return Maybe.empty();
        }

        return Maybe.from(bestLocation);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String getVersionName(@NonNull Context context) {
        PackageManager manager = context.getPackageManager();

        try {
            return manager
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("all")
    public static Locale getCurrentLocale(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

}
