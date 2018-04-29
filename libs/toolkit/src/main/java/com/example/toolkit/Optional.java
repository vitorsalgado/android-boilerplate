package com.example.toolkit;

import android.support.annotation.Nullable;

public class Optional<T> {
  private final T value;

  private Optional(T value) {
    this.value = value;
  }

  public static <T> Optional<T> from(T value) {
    if (value == null) {
      throw new NullPointerException();
    }

    return new Optional<>(value);
  }

  public static <T> Optional<T> fromNullable(@Nullable T value) {
    return new Optional<>(value);
  }

  public static <T> Optional<T> empty() {
    return new Optional<>(null);
  }

  public boolean isPresent() {
    return value != null;
  }

  public boolean isNotPresent() {
    return value == null;
  }

  public T get() {
    return value;
  }

  public T getOrDefault(T defaultValue) {
    if (isPresent()) {
      return value;
    }

    return defaultValue;
  }
}
