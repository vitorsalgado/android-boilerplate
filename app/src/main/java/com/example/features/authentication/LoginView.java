package com.example.features.authentication;

import com.example.features.BaseView;

public interface LoginView extends BaseView {
    void onLoginSuccess();

    void onLoginError(Throwable ex);

    void onLogout();
}
