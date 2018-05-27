package br.com.vitorsalgado.example.api.gateway.dtos

data class OAuthResponse(
  val accessToken: String,
  val refreshToken: String,
  val state: String,
  val scopes: List<String>,
  val tokenType: String,
  val expiresIn: Long
)
