package br.com.vitorsalgado.example.api.graphapi

data class Config(
  internal val uri: String,
  internal val cacheDir: String,
  internal val cacheSize: Int
)
