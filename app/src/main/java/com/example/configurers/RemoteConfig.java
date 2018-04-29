package com.example.configurers;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import com.example.BuildConfig;

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
