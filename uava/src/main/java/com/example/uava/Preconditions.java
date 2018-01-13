package com.example.uava;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Preconditions {
	private Preconditions() {
	}

	@NonNull
	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}

		return reference;
	}

	@NonNull
	public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
		if (reference == null) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}

		return reference;
	}

	@NonNull
	public static String checkNotNullOrEmpty(String value, @Nullable String errorMessage) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException(errorMessage);
		}

		return value;
	}

	public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
		if (!expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}
}
