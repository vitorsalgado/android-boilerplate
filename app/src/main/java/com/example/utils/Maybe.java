package com.example.utils;

import android.support.annotation.Nullable;

public class Maybe<T> {
    private final T value;

    Maybe(T value) {
        this.value = value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isNotPresent() {
        return value == null;
    }

    public static <T> Maybe<T> from(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        return new Maybe<T>(value);
    }

    public static <T> Maybe<T> fromNullable(@Nullable T value) {
        return new Maybe<T>(value);
    }

    public static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
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
