package com.example.data.net.facebook.token;

import com.facebook.AccessToken;

public class AccessTokenSdkProvider implements AccessTokenProvider {
    private static AccessTokenProvider instance;

    public static AccessTokenProvider getInstance() {
        if (instance == null) {
            instance = new AccessTokenSdkProvider();
        }

        return instance;
    }

    @Override
    public AccessToken getCurrent() {
        return AccessToken.getCurrentAccessToken();
    }
}
