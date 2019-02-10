package br.com.vitorsalgado.example.analytics

import android.app.Activity

interface ActivityProvider {
  fun currentActivity(): Activity
}
