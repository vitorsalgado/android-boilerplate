package br.com.vitorsalgado.example.services.authenticator

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.accounts.AccountManager.KEY_BOOLEAN_RESULT
import android.accounts.NetworkErrorException
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import br.com.vitorsalgado.example.api.bff.Api
import br.com.vitorsalgado.example.api.bff.dtos.OAuthResponse
import br.com.vitorsalgado.example.features.authentication.LoginActivity
import br.com.vitorsalgado.example.services.authenticator.AuthConstants.Companion.ACCOUNT_AUTHTOKEN_TYPE
import br.com.vitorsalgado.example.services.authenticator.AuthConstants.Companion.ACCOUNT_EXPIRESIN
import br.com.vitorsalgado.example.services.authenticator.AuthConstants.Companion.ACCOUNT_REFRESHTOKEN
import br.com.vitorsalgado.example.utils.LogUtility
import java.util.UUID

class AccountAuthenticator internal constructor(private val context: Context, private val api: Api) : AbstractAccountAuthenticator(context) {

  override fun editProperties(accountAuthenticatorResponse: AccountAuthenticatorResponse, s: String): Bundle? {
    return null
  }

  @Throws(NetworkErrorException::class)
  override fun addAccount(
    accountAuthenticatorResponse: AccountAuthenticatorResponse,
    accountType: String,
    authTokenType: String,
    requiredFeatures: Array<String>,
    options: Bundle
  ): Bundle {
    val intent = LoginActivity.newClearIntent(context)

    intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
    intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse)
    intent.putExtra(ACCOUNT_AUTHTOKEN_TYPE, authTokenType)

    val bundle = Bundle()
    bundle.putParcelable(AccountManager.KEY_INTENT, intent)

    return bundle
  }

  @Throws(NetworkErrorException::class)
  override fun confirmCredentials(
    accountAuthenticatorResponse: AccountAuthenticatorResponse,
    account: Account,
    bundle: Bundle
  ): Bundle? {
    return null
  }

  @Throws(NetworkErrorException::class)
  override fun getAuthToken(
    response: AccountAuthenticatorResponse,
    account: Account,
    authTokenType: String,
    options: Bundle
  ): Bundle {
    LogUtility.d("getAuthToken() account=" + account.name + " type=" + account.type)

    val accountManager = AccountManager.get(context)
    val bundle = Bundle()

    if (authTokenType != ACCOUNT_AUTHTOKEN_TYPE) {
      LogUtility.d("invalid authTokenType$authTokenType")
      bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType")

      return bundle
    }

    var authToken = accountManager.peekAuthToken(account, authTokenType)
    val refreshToken = accountManager.getUserData(account, ACCOUNT_REFRESHTOKEN)

    // no token data. user needs to login
    if (TextUtils.isEmpty(authToken) || TextUtils.isEmpty(refreshToken)) {
      accountManager.invalidateAuthToken(account.type, authToken)

      val intent = LoginActivity.newClearIntent(context)

      intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
      intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account.type)
      intent.putExtra(ACCOUNT_AUTHTOKEN_TYPE, authTokenType)

      bundle.putParcelable(AccountManager.KEY_INTENT, intent)

      return bundle
    }

    val exp = accountManager.getUserData(account, ACCOUNT_EXPIRESIN)
    val expiresIn = java.lang.Long.parseLong(exp)
    val oauth: OAuthResponse?

    if (AuthUtils.isAccessTokenExpired(expiresIn)) {
      val state = UUID.randomUUID().toString()
      oauth = api.refreshToken("", state, authToken, refreshToken)
        .blockingFirst()
        .body

      if (state != oauth!!.state) {
        throw IllegalStateException("state informed does not match the one returned from server response!")
      }

      authToken = oauth.accessToken

      accountManager.setAuthToken(account, authTokenType, authToken)
      accountManager.setUserData(account, ACCOUNT_REFRESHTOKEN, oauth.refreshToken)
      accountManager.setUserData(account, ACCOUNT_EXPIRESIN, oauth.expiresIn.toString())
    }

    bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
    bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
    bundle.putString(AccountManager.KEY_AUTHTOKEN, authToken)

    return bundle
  }

  override fun getAuthTokenLabel(authTokenType: String): String? {
    return if (authTokenType == ACCOUNT_AUTHTOKEN_TYPE) authTokenType else null
  }

  @Throws(NetworkErrorException::class)
  override fun updateCredentials(
    accountAuthenticatorResponse: AccountAuthenticatorResponse,
    account: Account,
    s: String,
    bundle: Bundle
  ): Bundle? {
    return null
  }

  @Throws(NetworkErrorException::class)
  override fun hasFeatures(
    accountAuthenticatorResponse: AccountAuthenticatorResponse,
    account: Account,
    strings: Array<String>
  ): Bundle {
    val result = Bundle()
    result.putBoolean(KEY_BOOLEAN_RESULT, false)
    return result
  }
}
