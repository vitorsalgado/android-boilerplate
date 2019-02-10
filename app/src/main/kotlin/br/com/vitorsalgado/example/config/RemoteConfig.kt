package br.com.vitorsalgado.example.config

import br.com.vitorsalgado.example.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfig {
  fun setup() =
    FirebaseRemoteConfig
      .getInstance()
      .setConfigSettings(
        FirebaseRemoteConfigSettings.Builder()
          .setDeveloperModeEnabled(BuildConfig.DEBUG)
          .build()
      )
}
