package com.example.services.push;

import com.example.android.utils.LogUtility;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
	@Override
	public void onMessageReceived(RemoteMessage message) {
		LogUtility.d(message.getMessageId());
		LogUtility.d(message.getFrom());
		LogUtility.d(message.getTo());
		LogUtility.d(message.getCollapseKey());
		LogUtility.d(message.getMessageType());
		LogUtility.d(String.valueOf(message.getSentTime()));
	}
}
