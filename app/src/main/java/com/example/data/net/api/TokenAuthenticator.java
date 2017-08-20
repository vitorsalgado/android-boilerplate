package com.example.data.net.api;

import com.example.AppContext;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
	private RefreshHandler refreshHandler;

	@Override
	public Request authenticate(Route route, Response response) throws IOException {
		if (!refreshHandler.refreshToken()) {
			return null;
		}

		return response.request().newBuilder()
				.header("authorization", AppContext.getAccessToken())
				.build();
	}

	void setRefreshHandler(RefreshHandler refreshHandler) {
		this.refreshHandler = refreshHandler;
	}
}
