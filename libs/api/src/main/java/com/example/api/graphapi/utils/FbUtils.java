package com.example.api.graphapi.utils;

public final class FbUtils {
  public static String convert(String[] args) {
    StringBuilder fields = new StringBuilder();

    for (String arg : args) {
      fields.append(arg).append(',');
    }

    return removeLastComma(fields.toString());
  }

  private static String removeLastComma(String str) {
    str = str.trim();

    if (str.length() > 0 && str.charAt(str.length() - 1) == ',') {
      str = str.substring(0, str.length() - 1);
    }

    return str;
  }
}
