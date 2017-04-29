package com.example.data.net.api;

import com.example.data.models.SignInRequest;
import com.example.data.models.SignInResponse;

import retrofit2.http.Body;
import rx.Observable;

public interface Api {
    Observable<SignInResponse> signIn(@Body SignInRequest request);
}
