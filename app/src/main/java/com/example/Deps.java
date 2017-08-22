package com.example;

import com.example.data.net.api.Api;
import com.example.data.net.api.OAuthApi;
import com.example.data.net.facebook.GraphApi;
import com.example.data.net.facebook.token.FBAccessTokenProvider;
import com.example.data.net.support.SupportApi;
import com.example.features.authentication.AuthenticationManager;
import com.example.utils.FileUtils;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

public interface Deps {
	AuthenticationManager authenticationManager();

	SupportApi supportApi();

	GraphApi graphApi();

	Api api();

	OAuthApi oauthApi();

	LoginManager fbLoginManager();

	CallbackManager fbCallbackManager();

	FBAccessTokenProvider fbAccessTokenProvider();
}
