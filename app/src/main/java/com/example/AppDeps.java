package com.example;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.net.api.Api;
import com.example.data.net.api.OAuthApi;
import com.example.data.net.facebook.GraphApi;
import com.example.data.net.facebook.token.FBAccessTokenProvider;
import com.example.data.net.support.SupportApi;
import com.example.features.authentication.AuthenticationManager;
import com.example.utils.FileUtils;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

public class AppDeps {
	private static Deps mDeps;

	public static void setUp(Deps deps) {
		mDeps = deps;
	}

	// Managers

	public static AuthenticationManager authenticationManager() {
		return mDeps.authenticationManager();
	}

	// Network

	public static SupportApi supportApi() {
		return mDeps.supportApi();
	}

	public static GraphApi graphApi() {
		return mDeps.graphApi();
	}

	public static Api api() {
		return mDeps.api();
	}

	public static OAuthApi oauthApi() {
		return mDeps.oauthApi();
	}

	// Libraries

	public static FileUtils fileUtils() {
		return mDeps.fileUtils();
	}

	public static LoginManager fbLoginManager() {
		return mDeps.fbLoginManager();
	}

	public static CallbackManager fbCallbackManager() {
		return mDeps.fbCallbackManager();
	}

	public static AppContext appContext(@NonNull Context context) {
		return null;
	}

	public static FBAccessTokenProvider fbAccessTokenProvider() {
		return mDeps.fbAccessTokenProvider();
	}
}
