package com.example.push;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.vitorsalgado.androidstarter.logger.CLog;

public class MessagingService extends FirebaseMessagingService {
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		CLog.d(remoteMessage.getFrom());
	}
}
