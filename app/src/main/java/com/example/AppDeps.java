package com.example;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.net.api.Api;
import com.example.data.net.api.OAuthApi;
import com.example.data.net.facebook.FbGraphApi;
import com.example.data.net.facebook.GraphApi;
import com.example.data.net.facebook.token.FBAccessTokenProvider;
import com.example.data.net.facebook.token.FBSdkAccessTokenProvider;
import com.example.data.net.support.SimpleSupportApi;
import com.example.data.net.support.SupportApi;
import com.example.features.authentication.AuthenticationManager;
import com.example.utils.FileUtils;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.io.File;
import java.lang.ref.WeakReference;

public class AppDeps {
	private static WeakReference<App> application;

	static void setUp(@NonNull App app) {
		application = new WeakReference<>(app);
	}

	// Managers

	public static AuthenticationManager authenticationManager() {
		return new AuthenticationManager(oauthApi(), api());
	}

	// Network

	public static SupportApi supportApi() {
		return SimpleSupportApi.getInstance();
	}

	public static GraphApi graphApi() {
		return FbGraphApi.getInstance(supportApi(), fbAccessTokenProvider());
	}

	public static Api api() {
		return Api.Builder.get(BuildConfig.API_URL, new File("com.example.network.cache"));
	}

	public static OAuthApi oauthApi() {
		return OAuthApi.Builder.get();
	}

	// Libraries

	public static FileUtils fileUtils() {
		return new FileUtils(application.get());
	}

	public static LoginManager fbLoginManager() {
		return LoginManager.getInstance();
	}

	public static CallbackManager fbCallbackManager() {
		return CallbackManager.Factory.create();
	}

	public static AppContext appContext(@NonNull Context context) {
		return null;
	}

	public static FBAccessTokenProvider fbAccessTokenProvider() {
		return FBSdkAccessTokenProvider.getInstance();
	}
}
