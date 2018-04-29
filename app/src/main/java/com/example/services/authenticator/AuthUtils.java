package com.example.services.authenticator;

import java.util.Calendar;
import java.util.Date;

final class AuthUtils {
  static boolean isAccessTokenExpired(long expiresIn) {
    Calendar c = Calendar.getInstance();
    Date nowDate = c.getTime();

    c.setTimeInMillis(expiresIn);
    Date expireDate = c.getTime();

    int result = nowDate.compareTo(expireDate);

    return result == -1;
  }
}
