package com.example.features.authentication;

import com.example.features.PresenterView;

public interface OAuthView extends PresenterView {
	void showService(LoginServiceConfiguration oauthConfig);

	void close();

	void showLoginDone();

	void showLoginError();
}
