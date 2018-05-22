package com.example.features.authentication

import com.example.features.AbstractViewModel
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

class LoginViewModel : FacebookCallback<LoginResult>, AbstractViewModel() {
  override fun onSuccess(result: LoginResult?) {

  }

  override fun onCancel() {
  }

  override fun onError(error: FacebookException?) {
  }
}
