package com.example.push;

import android.support.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.example.logger.CLog;

public class InstanceIdService extends FirebaseInstanceIdService {
	@Override
	public void onTokenRefresh() {
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();

		if (refreshedToken == null || refreshedToken.isEmpty()) {
			return;
		}

		sendRegistrationToServer(refreshedToken);
	}

	private void sendRegistrationToServer(@NonNull String refreshedToken) {
		CLog.d("( FIREBASE -> onTokenRefresh ) ", refreshedToken);
	}
}
