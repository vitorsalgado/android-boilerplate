package com.example.services.authenticator

import java.util.*

internal object AuthUtils {
  fun isAccessTokenExpired(expiresIn: Long): Boolean {
    val c = Calendar.getInstance()
    val nowDate = c.time

    c.timeInMillis = expiresIn
    val expireDate = c.time

    val result = nowDate.compareTo(expireDate)

    return result == -1
  }
}
