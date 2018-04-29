package com.example.api;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Collections;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

public class HttpClientWithPinningProvider {
  private static final OkHttpClient CLIENT = init();

  private static OkHttpClient init() {
    ConnectionSpec.Builder spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
      .tlsVersions(TlsVersion.TLS_1_2);
    OkHttpClient.Builder client = new OkHttpClient.Builder()
      .connectionSpecs(Collections.singletonList(spec.build()));
    // This setup is necessary because API 16-18 support TLS 1.2,
    // but use 1.0 as default.
    setupTls(client);
    setupPinning(client);

    return client.build();
  }

  private static void setupPinning(OkHttpClient.Builder client) {
    // Implement certificate pinning here
  }

  private static void setupTls(OkHttpClient.Builder builder) {
    try {
      TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      factory.init((KeyStore) null);
      for (TrustManager trustManager : factory.getTrustManagers()) {
        if (trustManager instanceof X509TrustManager) {
          builder.sslSocketFactory(new Tls12SslSocketFactory(), (X509TrustManager) trustManager);
          break;
        }
      }
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  public static OkHttpClient getClient() {
    return CLIENT;
  }
}
