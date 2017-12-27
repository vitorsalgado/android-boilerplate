package com.example.config;

import com.example.BuildConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public final class RemoteConfig {
	public static void setup() {
		final FirebaseRemoteConfigSettings remoteConfigSettings =
			new FirebaseRemoteConfigSettings
				.Builder()
				.setDeveloperModeEnabled(BuildConfig.DEBUG)
				.build();

		FirebaseRemoteConfig
			.getInstance()
			.setConfigSettings(remoteConfigSettings);
	}
}
