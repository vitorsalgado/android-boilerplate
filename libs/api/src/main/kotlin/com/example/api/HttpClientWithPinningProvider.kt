package com.example.api

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import java.security.GeneralSecurityException
import java.security.KeyStore
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object HttpClientWithPinningProvider {
  val client = init()

  private fun init(): OkHttpClient {
    val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
      .tlsVersions(TlsVersion.TLS_1_2)
    val client = OkHttpClient.Builder()
      .connectionSpecs(listOf<ConnectionSpec>(spec.build()))
    // This setup is necessary because API 16-18 support TLS 1.2,
    // but use 1.0 as default.
    setupTls(client)
    setupPinning(client)

    return client.build()
  }

  private fun setupPinning(client: OkHttpClient.Builder) {
    // Implement certificate pinning here
  }

  private fun setupTls(builder: OkHttpClient.Builder) {
    try {
      val factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
      factory.init(null as KeyStore?)
      for (trustManager in factory.trustManagers) {
        if (trustManager is X509TrustManager) {
          builder.sslSocketFactory(Tls12SslSocketFactory(), trustManager)
          break
        }
      }
    } catch (e: GeneralSecurityException) {
      throw RuntimeException(e)
    }
  }
}
