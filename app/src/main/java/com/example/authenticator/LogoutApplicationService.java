package com.example.authenticator;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.facebook.drawee.backends.pipeline.Fresco;

import javax.inject.Inject;

import io.reactivex.Completable;

public class LogoutApplicationService implements LogoutService {
	private final Context context;
	private final SharedPreferences sharedPreferences;

	@Inject
	LogoutApplicationService(
		@NonNull Context context,
		@NonNull SharedPreferences sharedPreferences) {
		this.context = context;
		this.sharedPreferences = sharedPreferences;
	}

	@WorkerThread
	@Override
	public Completable logout() {
		return Completable.create(emitter -> {
			sharedPreferences.edit().clear().apply();
			Fresco.getImagePipeline().clearCaches();

			final AccountManager accountManagerWithContext = AccountManager.get(context);
			final Account[] accounts = accountManagerWithContext
				.getAccountsByType(AuthConstants.ACCOUNT_TYPE);

			if (accounts.length > 0) {
				final AccountManagerFuture<Boolean> removeAccountFuture
					= accountManagerWithContext.removeAccount(accounts[0], null, null);

				try {
					removeAccountFuture.getResult();
					emitter.onComplete();
					return;
				} catch (Exception ex) {
					emitter.onError(ex);
					return;
				}
			}

			emitter.onComplete();
		});
	}
}
