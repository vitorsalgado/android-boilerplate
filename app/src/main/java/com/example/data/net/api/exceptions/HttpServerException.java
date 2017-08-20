package com.example.data.net.api.exceptions;

import com.example.data.net.api.dtos.ApiError;

public class HttpServerException extends BaseHttpException {
	public HttpServerException(ApiError apiError) {
		super(apiError);
	}
}
