package br.com.vitorsalgado.example.config

import br.com.vitorsalgado.example.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfig {
  fun setup() {
    val remoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
      .setDeveloperModeEnabled(BuildConfig.DEBUG)
      .build()

    FirebaseRemoteConfig
      .getInstance()
      .setConfigSettings(remoteConfigSettings)
  }
}
