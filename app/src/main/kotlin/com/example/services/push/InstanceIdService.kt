package com.example.services.push

import com.example.utils.LogUtility
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class InstanceIdService : FirebaseInstanceIdService() {
  override fun onTokenRefresh() {
    val refreshedToken = FirebaseInstanceId.getInstance().token

    if (refreshedToken == null || refreshedToken.isEmpty()) {
      return
    }

    sendRegistrationToServer(refreshedToken)
  }

  private fun sendRegistrationToServer(refreshedToken: String) {
    LogUtility.d("( FIREBASE -> onTokenRefresh ) ", refreshedToken)
  }
}
