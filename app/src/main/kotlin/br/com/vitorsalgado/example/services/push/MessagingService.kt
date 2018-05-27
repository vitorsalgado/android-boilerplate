package br.com.vitorsalgado.example.services.push

import br.com.vitorsalgado.example.utils.LogUtility
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
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
}
