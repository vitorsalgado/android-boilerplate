package com.example.data.managers;

import com.example.data.models.SignInResponse;
import com.facebook.AccessToken;

import io.reactivex.Observable;

public interface AuthenticationManager {
    Observable<SignInResponse> signIn(AccessToken accessToken);

    Observable<Void> signOut();
}
