package br.com.vitorsalgado.androidstarter.api.dtos;

import java.util.List;

public class OAuthResponse {
	private String access_token;
	private String refresh_token;
	private String state;
	private List<String> scopes;
	private String token_type;
	private long expires_in;

	public OAuthResponse(String access_token, String refresh_token, String state, List<String> scopes, String token_type, long expires_in) {
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.state = state;
		this.scopes = scopes;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}

	public String getAccessToken() {
		return access_token;
	}

	public String getRefreshToken() {
		return refresh_token;
	}

	public String getState() {
		return state;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public String getTokenType() {
		return token_type;
	}

	public long getExpiresIn() {
		return expires_in;
	}
}
