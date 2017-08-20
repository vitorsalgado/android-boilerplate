package com.example.data.net.facebook.token;

import com.facebook.AccessToken;

public class FBSdkAccessTokenProvider implements FBAccessTokenProvider {
	private static FBAccessTokenProvider instance;

	public static FBAccessTokenProvider getInstance() {
		if (instance == null) {
			instance = new FBSdkAccessTokenProvider();
		}

		return instance;
	}

	@Override
	public AccessToken getCurrent() {
		return AccessToken.getCurrentAccessToken();
	}
}
