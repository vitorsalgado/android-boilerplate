package com.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.data.net.api.dtos.TokenResponse;
import com.google.gson.Gson;
import com.orhanobut.hawk.GsonParser;
import com.orhanobut.hawk.Hawk;

import java.util.Calendar;
import java.util.Date;

public final class AppContext {
	private static final String TAG = "com.example.AppContext";

	private static final String ACCESS_TOKEN_KEY = "access_token";
	private static final String REFRESH_TOKEN_KEY = "refresh_token";
	private static final String TOKEN_EXPIRATION_KEY = "expires_in";

	public static void setUp(@NonNull Context context) {
		Hawk.init(context)
				.setLogInterceptor(message -> Log.d(TAG, message))
				.setParser(new GsonParser(new Gson()))
				.build();
	}

	public static void setSession(@NonNull TokenResponse tokenResponse) {
		int expiresIn = tokenResponse.getExpiresIn();

		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, expiresIn);

		Hawk.put(ACCESS_TOKEN_KEY, tokenResponse.getAccessToken());
		Hawk.put(REFRESH_TOKEN_KEY, tokenResponse.getRefreshToken());
		Hawk.put(TOKEN_EXPIRATION_KEY, expiresIn);
	}

	public static void invalidate() {
		Hawk.deleteAll();
	}

	public static boolean isAuth() {
		return Hawk.contains(ACCESS_TOKEN_KEY);
	}

	public static String getAccessToken() {
		return Hawk.get(ACCESS_TOKEN_KEY);
	}

	public static String getRefreshToken() {
		return Hawk.get(REFRESH_TOKEN_KEY);
	}

	public static boolean isAccessTokenExpired() {
		long expireTime = Hawk.get(TOKEN_EXPIRATION_KEY);

		Calendar c = Calendar.getInstance();
		Date nowDate = c.getTime();

		c.setTimeInMillis(expireTime);
		Date expireDate = c.getTime();

		int result = nowDate.compareTo(expireDate);

		return result == -1;
	}
}
