package com.example.features.authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.data.net.api.dtos.TokenResponse;
import com.example.features.BasePresenter;
import com.example.utils.espresso.EspressoIdlingResource;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import io.reactivex.observers.DisposableObserver;

import static com.example.utils.Preconditions.checkNotNull;

public class LoginPresenter extends BasePresenter<LoginView> implements FacebookCallback<LoginResult> {
	private static final String[] FB_PERMISSIONS = {"public_profile", "user_photos", "email", "user_birthday", "user_hometown"};

	private final AuthenticationManager mAuthenticationManager;
	private final CallbackManager mCallbackManager;

	LoginPresenter(
			@NonNull LoginView loginView,
			@NonNull AuthenticationManager authenticationManager,
			@NonNull CallbackManager callbackManager) {

		super(loginView);

		mAuthenticationManager = checkNotNull(authenticationManager, "authenticationManager cannot be null.");
		mCallbackManager = checkNotNull(callbackManager, "callbackManager cannot be null.");
	}

	void start() {
		LoginManager.getInstance().registerCallback(mCallbackManager, this);
	}

	void result(int requestCode, int resultCode, Intent data) {
		mCallbackManager.onActivityResult(requestCode, resultCode, data);
	}

	void loginWithFacebook(@NonNull Activity activity) {
		EspressoIdlingResource.increment();
		LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList(FB_PERMISSIONS));
	}

	void logout() {
		getView().showLoading();

		subscribeOnIO(mAuthenticationManager.signOut(), responseValues -> {
			getView().loaded();
			getView().onLogout();
		});
	}

	// Facebook Callback Implementation

	@Override
	public void onSuccess(LoginResult loginResult) {
		getView().showLoading();

		subscribeOnIO(mAuthenticationManager.signIn(loginResult.getAccessToken()), new DisposableObserver<TokenResponse>() {
			@Override
			public void onComplete() {

			}

			@Override
			public void onError(Throwable e) {
				getView().onLoginError(e);
			}

			@Override
			public void onNext(TokenResponse response) {
				getView().onLoginSuccess();
			}
		});
	}

	@Override
	public void onCancel() {
		getView().loaded();
	}

	@Override
	public void onError(FacebookException error) {
		getView().onLoginError(error);
	}
}