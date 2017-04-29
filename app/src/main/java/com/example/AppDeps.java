package com.example;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.managers.AuthenticationManager;
import com.example.data.net.facebook.FbGraphApi;
import com.example.data.net.facebook.GraphApi;
import com.example.data.net.facebook.token.AccessTokenProvider;
import com.example.data.net.facebook.token.AccessTokenSdkProvider;
import com.example.data.net.support.SimpleSupportApi;
import com.example.data.net.support.SupportApi;
import com.example.utils.FileUtils;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.lang.ref.WeakReference;

public class AppDeps {
    private static WeakReference<App> application;

    public static void init(@NonNull App app) {
        application = new WeakReference<App>(app);
    }

    // Managers

    public static AuthenticationManager authenticationManager() {
        return null;
    }

    // Network

    public static SupportApi supportApi() {
        return SimpleSupportApi.getInstance();
    }

    public static GraphApi graphApi() {
        return FbGraphApi.getInstance(supportApi(), fbAccessTokenProvider());
    }

    // Libraries

    public static FileUtils fileUtils() {
        return new FileUtils(application.get());
    }

    public static LoginManager fbLoginManager() {
        return LoginManager.getInstance();
    }

    public static CallbackManager fbCallbackManager() {
        return CallbackManager.Factory.create();
    }

    public static AppContext appContext(@NonNull Context context) {
        return null;
    }

    public static AccessTokenProvider fbAccessTokenProvider() {
        return AccessTokenSdkProvider.getInstance();
    }
}
