package br.com.vitorsalgado.example.services.authenticator

interface AuthConstants {
  companion object {
    const val ACCOUNT_TYPE = "io.synastry"
    const val ACCOUNT_EXPIRESIN = "expires_in"
    const val ACCOUNT_REFRESHTOKEN = "refresh_token"
    const val ACCOUNT_AUTHTOKEN_TYPE = "auth_type"
  }
}
