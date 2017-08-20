package com.example.data.net.api;

import com.example.AppContext;
import com.example.AppDefaults;
import com.example.data.net.api.dtos.TokenResponse;

import java.util.UUID;

public class RefreshHandler {
	private OAuthApi mOAuthApi;

	public boolean refreshToken() {
		String state = UUID.randomUUID().toString();
		String accessToken = AppContext.getAccessToken();
		String refreshToken = AppContext.getRefreshToken();
		TokenResponse tokenResponse;

		tokenResponse = mOAuthApi.oauthToken(AppDefaults.ClientId, "refresh_token", state, null, accessToken, refreshToken)
				.blockingSingle();

		if (!tokenResponse.getState().equals(state)) {
			throw new IllegalStateException("Response \"state\" came with a value which does not match the one provided on request");
		}

		if (accessToken.equals(tokenResponse.getAccessToken()) || refreshToken.equals(tokenResponse.getRefreshToken())) {
			return false;
		}

		AppContext.setSession(tokenResponse);

		return true;
	}

	public void setOAuthApi(OAuthApi oAuthApi) {
		mOAuthApi = oAuthApi;
	}
}
