package com.example.features.authentication;

import android.support.annotation.NonNull;

import com.example.AppContext;
import com.example.AppDefaults;
import com.example.data.net.api.Api;
import com.example.data.net.api.OAuthApi;
import com.example.data.net.api.dtos.TokenResponse;
import com.facebook.AccessToken;

import java.util.UUID;

import io.reactivex.Observable;

public class AuthenticationManager {
	private final OAuthApi mOauthApi;
	private final Api mApi;

	public AuthenticationManager(@NonNull OAuthApi oAuthApi, @NonNull Api api) {
		mOauthApi = oAuthApi;
		mApi = api;
	}

	public Observable<TokenResponse> signIn(AccessToken facebookAccessToken) {
		String state = UUID.randomUUID().toString();

		return mOauthApi.oauthToken(AppDefaults.ClientId, "facebook", state, facebookAccessToken.getToken(), null, null)
				.doOnNext(AppContext::setSession);
	}

	public Observable<Void> signOut() {
		return mApi.signOut()
				.doOnNext(x -> AppContext.invalidate());
	}
}
