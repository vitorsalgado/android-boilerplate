package com.example.interactions.authentication;

import okhttp3.HttpUrl;

public class FacebookOAuthFragment extends AbstractOAuthFragment {
	@Override
	protected String getOAuthServiceName() {
		return "facebook";
	}

	@Override
	protected String generateURL(LoginServiceConfiguration oauthConfig) {
		return new HttpUrl.Builder().scheme("https")
				.host("www.facebook.com")
				.addPathSegment("v2.10")
				.addPathSegment("dialog")
				.addPathSegment("oauth")
				.addQueryParameter("client_id", oauthConfig.getKey())
				.addQueryParameter("redirect_uri", "http://192.168.1.34:3002/_oauth/facebook?close")
				.addQueryParameter("display", "popup")
				.addQueryParameter("scope", "email")
				.addQueryParameter("state", getStateString())
				.build()
				.toString();
	}
}
