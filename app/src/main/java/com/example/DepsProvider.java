package com.example;

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

public class DepsProvider implements Deps {
	private static WeakReference<App> mApplication;

	DepsProvider(@NonNull App application) {
		mApplication = new WeakReference<>(application);
	}

	@Override
	public AuthenticationManager authenticationManager() {
		return new AuthenticationManager(oauthApi(), api());
	}

	@Override
	public SupportApi supportApi() {
		return SimpleSupportApi.getInstance();
	}

	@Override
	public GraphApi graphApi() {
		return FbGraphApi.getInstance(supportApi(), fbAccessTokenProvider());
	}

	@Override
	public Api api() {
		return Api.Builder.get(BuildConfig.API_URL, new File("com.example.network.cache"));
	}

	@Override
	public OAuthApi oauthApi() {
		return OAuthApi.Builder.get();
	}

	@Override
	public FileUtils fileUtils() {
		return new FileUtils(mApplication.get());
	}

	@Override
	public LoginManager fbLoginManager() {
		return LoginManager.getInstance();
	}

	@Override
	public CallbackManager fbCallbackManager() {
		return CallbackManager.Factory.create();
	}

	@Override
	public FBAccessTokenProvider fbAccessTokenProvider() {
		return FBSdkAccessTokenProvider.getInstance();
	}
}
