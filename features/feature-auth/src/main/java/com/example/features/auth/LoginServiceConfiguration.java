package com.example.features.auth;

public class LoginServiceConfiguration {
	private String id;
	private String service;
	private String key;

	public LoginServiceConfiguration(String id, String service, String key) {
		this.id = id;
		this.service = service;
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public String getService() {
		return service;
	}

	public String getKey() {
		return key;
	}
}
