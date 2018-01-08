package com.example.authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Objects;

import com.example.api.Api;

import static android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT;

public class AccountAuthenticatorService extends Service {
	private AccountAuthenticator authenticator = null;
	Api api;

	@Override
	public IBinder onBind(Intent intent) {
		if (intent != null && Objects.equals(intent.getAction(), ACTION_AUTHENTICATOR_INTENT)) {
			return getAuthenticator().getIBinder();
		}

		return null;
	}

	private AccountAuthenticator getAuthenticator() {
		if (authenticator == null) {
			authenticator = new AccountAuthenticator(this, api);
		}

		return authenticator;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		authenticator = null;
		super.onDestroy();
	}
}
