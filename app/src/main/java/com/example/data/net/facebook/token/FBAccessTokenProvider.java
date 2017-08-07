package com.example.data.net.facebook.token;

import com.facebook.AccessToken;

public interface FBAccessTokenProvider {
    AccessToken getCurrent();
}
