package br.com.vitorsalgado.example.services.push

import br.com.vitorsalgado.example.utils.LogUtility
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DefaultFirebaseMessagingService : FirebaseMessagingService() {
  override fun onMessageReceived(message: RemoteMessage?) {
    with(LogUtility) {
      d(message?.messageId!!)
      d(message.from!!)
      d(message.to!!)
      d(message.collapseKey!!)
      d(message.messageType!!)
      d(message.sentTime.toString())
    }
  }

  override fun onNewToken(token: String?) {
    super.onNewToken(token)

    if (token == null || token.isEmpty()) {
      return
    }

    sendRegistrationToServer(token)
  }

  private fun sendRegistrationToServer(refreshedToken: String) {
    LogUtility.d("( FIREBASE -> onTokenRefresh ) ", refreshedToken)
  }
}
