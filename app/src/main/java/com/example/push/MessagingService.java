package com.example.push;

import com.example.android.utils.LogUtility;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		LogUtility.d(remoteMessage.getFrom());
	}
}
