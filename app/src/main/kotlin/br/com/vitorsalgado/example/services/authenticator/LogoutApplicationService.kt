package br.com.vitorsalgado.example.services.authenticator

import android.accounts.AccountManager
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import com.facebook.drawee.backends.pipeline.Fresco
import io.reactivex.Completable
import javax.inject.Inject

class LogoutApplicationService @Inject
internal constructor(
  private val context: Context,
  private val sharedPreferences: SharedPreferences
) : LogoutService {

  @WorkerThread
  override fun logout(): Completable {
    return Completable.create { emitter ->
      sharedPreferences.edit().clear().apply()
      Fresco.getImagePipeline().clearCaches()

      val accountManagerWithContext = AccountManager.get(context)
      val accounts = accountManagerWithContext
        .getAccountsByType(AuthConstants.ACCOUNT_TYPE)

      if (accounts.isNotEmpty()) {
        val authToken = accountManagerWithContext.peekAuthToken(accounts[0], AuthConstants.ACCOUNT_TYPE)
        accountManagerWithContext.invalidateAuthToken(AuthConstants.ACCOUNT_TYPE, authToken)
      }

      emitter.onComplete()
    }
  }
}
