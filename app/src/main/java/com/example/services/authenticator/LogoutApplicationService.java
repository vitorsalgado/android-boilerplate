package com.example.services.authenticator;

import android.accounts.Account;
import android.accounts.AccountManager;
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
        String authToken = accountManagerWithContext.peekAuthToken(accounts[0], AuthConstants.ACCOUNT_TYPE);
        accountManagerWithContext.invalidateAuthToken(AuthConstants.ACCOUNT_TYPE, authToken);
      }

      emitter.onComplete();
    });
  }
}
