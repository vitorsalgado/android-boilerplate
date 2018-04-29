package com.example.api.gateway;

import java.io.File;

public class Config {
  private final String uri;
  private final File cacheDir;
  private final String cacheName;
  private final int cacheSize;

  public Config(String uri, File cacheDir, String cacheName, int cacheSize) {
    this.cacheDir = cacheDir;
    this.cacheName = cacheName;
    this.cacheSize = cacheSize;
    this.uri = uri;
  }

  String getUri() {
    return uri;
  }

  File getCacheDir() {
    return cacheDir;
  }

  String getCacheName() {
    return cacheName;
  }

  int getCacheSize() {
    return cacheSize;
  }
}
