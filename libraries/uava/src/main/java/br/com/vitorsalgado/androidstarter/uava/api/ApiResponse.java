package br.com.vitorsalgado.androidstarter.uava.api;

import android.support.annotation.Nullable;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<T> {
	private final int code;
	@Nullable
	private final T body;
	@Nullable
	private final String errorMessage;

	public ApiResponse(Throwable error) {
		code = 500;
		body = null;
		errorMessage = error.getMessage();
	}

	ApiResponse(Response<T> response) {
		code = response.code();
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
			if (message == null || message.trim().length() == 0) {
				message = response.message();
			}
			errorMessage = message;
			body = null;
		}
	}

	public boolean isSuccessful() {
		return code >= 200 && code < 300;
	}
}
