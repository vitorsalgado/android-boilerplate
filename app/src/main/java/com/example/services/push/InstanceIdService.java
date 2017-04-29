package com.example.services.push;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "InstanceIdService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if (refreshedToken == null || refreshedToken.isEmpty()) {
            return;
        }

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(@NonNull String refreshedToken) {
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
