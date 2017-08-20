package com.example.data.net.api.dtos;

import java.util.List;

public class TokenResponse {
	private String access_token;
	private String refresh_token;
	private String token_type;
	private int expires_in;
	private List<String> scope;
	private String state;

	public String getAccessToken() {
		return access_token;
	}

	public String getRefreshToken() {
		return refresh_token;
	}

	public String getTokenType() {
		return token_type;
	}

	public int getExpiresIn() {
		return expires_in;
	}

	public List<String> getScope() {
		return scope;
	}

	public String getState() {
		return state;
	}
}
