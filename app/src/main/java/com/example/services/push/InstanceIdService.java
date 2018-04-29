package com.example.services.push;

import android.support.annotation.NonNull;

import com.example.android.utils.LogUtility;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

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
		LogUtility.d("( FIREBASE -> onTokenRefresh ) ", refreshedToken);
	}
}
