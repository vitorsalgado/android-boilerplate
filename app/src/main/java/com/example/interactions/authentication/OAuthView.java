package com.example.interactions.authentication;

import com.example.interactions.PresenterView;

public interface OAuthView extends PresenterView {
	void showService(LoginServiceConfiguration oauthConfig);

	void close();

	void showLoginDone();

	void showLoginError();
}
