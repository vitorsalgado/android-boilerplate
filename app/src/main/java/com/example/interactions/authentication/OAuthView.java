package com.example.interactions.authentication;

import com.example.interactions.AbstractView;

public interface OAuthView extends AbstractView {
	void showService(LoginServiceConfiguration oauthConfig);

	void close();

	void showLoginDone();

	void showLoginError();
}
