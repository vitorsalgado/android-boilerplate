package com.example.authenticator;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;
import static com.example.authenticator.AuthConstants.ACCOUNT_AUTHTOKEN_TYPE;
import static com.example.authenticator.AuthConstants.ACCOUNT_EXPIRESIN;
import static com.example.authenticator.AuthConstants.ACCOUNT_REFRESHTOKEN;

public class AccountAuthenticator extends AbstractAccountAuthenticator {
	private static final String TAG = AccountAuthenticator.class.getSimpleName();
	private final Context context;
	//private final Api openApi;

	AccountAuthenticator(Context context) {
		super(context);
		this.context = context;
		//this.openApi = openApi;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
		return null;
	}

	@Override
	public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
//		final Intent intent = LoginActivity.newClearIntent(context);
//
//		intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
//		intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
//		intent.putExtra(ACCOUNT_AUTHTOKEN_TYPE, authTokenType);

		final Bundle bundle = new Bundle();
//		bundle.putParcelable(AccountManager.KEY_INTENT, intent);

		return bundle;
	}

	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
		return null;
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
		Log.d(TAG, "getAuthToken() account=" + account.name + " type=" + account.type);

		final AccountManager accountManager = AccountManager.get(context);
		final Bundle bundle = new Bundle();

		if (!authTokenType.equals(ACCOUNT_AUTHTOKEN_TYPE)) {
			Log.d(TAG, "invalid authTokenType" + authTokenType);
			bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");

			return bundle;
		}

		String authToken = accountManager.peekAuthToken(account, authTokenType);
		String refreshToken = accountManager.getUserData(account, ACCOUNT_REFRESHTOKEN);

		// no token data. user needs to login
		if (TextUtils.isEmpty(authToken) || TextUtils.isEmpty(refreshToken)) {
			accountManager.invalidateAuthToken(account.type, authToken);

//			final Intent intent = LoginActivity.newClearIntent(context);
//
//			intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//			intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account.type);
//			intent.putExtra(ACCOUNT_AUTHTOKEN_TYPE, authTokenType);
//
//			bundle.putParcelable(AccountManager.KEY_INTENT, intent);

			return bundle;
		}

		String exp = accountManager.getUserData(account, ACCOUNT_EXPIRESIN);
		long expiresIn = Long.parseLong(exp);
//		OAuthResponseDto token;
//
//		if (AuthUtils.isAccessTokenExpired(expiresIn)) {
//			String state = UUID.randomUUID().toString();
//			token = openApi.refreshToken("", state, authToken, refreshToken).blockingFirst();
//
//			if (!state.equals(token.getState())) {
//				throw new IllegalStateException("state informed does not match the one returned from server response!");
//			}
//
//			authToken = token.getAccessToken();
//
//			accountManager.setAuthToken(account, authTokenType, authToken);
//			accountManager.setUserData(account, ACCOUNT_REFRESHTOKEN, token.getRefreshToken());
//			accountManager.setUserData(account, ACCOUNT_EXPIRESIN, String.valueOf(token.getExpiresIn()));
//		}

		bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
		bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
		bundle.putString(AccountManager.KEY_AUTHTOKEN, authToken);

		return bundle;
	}

	@Override
	public String getAuthTokenLabel(String authTokenType) {
		return authTokenType.equals(ACCOUNT_AUTHTOKEN_TYPE) ? authTokenType : null;
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
		return null;
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
		final Bundle result = new Bundle();
		result.putBoolean(KEY_BOOLEAN_RESULT, false);
		return result;
	}
}
