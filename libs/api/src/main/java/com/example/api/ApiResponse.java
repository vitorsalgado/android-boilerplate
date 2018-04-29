package com.example.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<T> {
	private final int statusCode;

	@Nullable
	private final T body;

	@Nullable
	private final String errorMessage;

	public ApiResponse(@NonNull Throwable error) {
		statusCode = 500;
		body = null;
		errorMessage = error.getMessage();
	}

	ApiResponse(@NonNull Response<T> response) {
		statusCode = response.code();

		if (response.isSuccessful()) {
			body = response.body();
			errorMessage = null;
		} else {
			String message = null;

			if (response.errorBody() != null) {
				try {
					message = response.errorBody().string();
				} catch (IOException ignored) {

				}
			}

			if (message == null || message.isEmpty()) {
				message = response.message();
			}

			errorMessage = message;
			body = null;
		}
	}

	public boolean isSuccessful() {
		return statusCode >= 200 && statusCode < 300;
	}

	@Nullable
	public T getBody() {
		return body;
	}
}
