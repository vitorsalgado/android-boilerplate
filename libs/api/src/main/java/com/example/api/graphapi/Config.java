package com.example.api.graphapi;

public final class Config {
	private final String uri;
	private final String cacheDir;
	private final int cacheSize;

	public Config(String uri, String cacheDir, int cacheSize) {
		this.uri = uri;
		this.cacheDir = cacheDir;
		this.cacheSize = cacheSize;
	}

	String getUri() {
		return uri;
	}

	String getCacheDir() {
		return cacheDir;
	}

	int getCacheSize() {
		return cacheSize;
	}
}
