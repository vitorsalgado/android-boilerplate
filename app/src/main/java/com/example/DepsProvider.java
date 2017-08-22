package com.example;

import com.example.data.net.api.Api;
import com.example.data.net.api.OAuthApi;
import com.example.data.net.facebook.FbGraphApi;
import com.example.data.net.facebook.GraphApi;
import com.example.data.net.facebook.token.FBAccessTokenProvider;
import com.example.data.net.facebook.token.FBSdkAccessTokenProvider;
import com.example.data.net.support.SimpleSupportApi;
import com.example.data.net.support.SupportApi;
import com.example.features.authentication.AuthenticationManager;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.io.File;

public class DepsProvider implements Deps {
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
