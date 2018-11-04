package br.com.vitorsalgado.example.services.authenticator

import android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT
import android.app.Service
import android.content.Intent
import android.os.IBinder
import br.com.vitorsalgado.example.api.bff.Api

class AccountAuthenticatorService : Service() {
  private var api: Api? = null
  private var authenticator: AccountAuthenticator? = null

  override fun onBind(intent: Intent?): IBinder? {
    return if (intent != null && intent.action == ACTION_AUTHENTICATOR_INTENT) {
      getAuthenticator().iBinder
    } else null
  }

  private fun getAuthenticator(): AccountAuthenticator {
    if (authenticator == null) {
      authenticator = AccountAuthenticator(this, this.api!!)
    }

    return authenticator as AccountAuthenticator
  }

  override fun onDestroy() {
    authenticator = null
    super.onDestroy()
  }
}
