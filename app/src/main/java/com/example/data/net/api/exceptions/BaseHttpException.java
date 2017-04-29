package com.example.data.net.api.exceptions;

import com.example.data.net.api.dtos.ApiError;

public abstract class BaseHttpException extends RuntimeException {
    protected final ApiError apiError;

    protected BaseHttpException(ApiError apiError) {
        super(apiError.getMessage());

        this.apiError = apiError;
    }

    protected BaseHttpException() {
        super();

        apiError = null;
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public String toString() {
        if (apiError == null) {
            return super.toString();
        }

        return apiError.getCode() + "\n" +
                apiError.getMessage() + "\n" +
                (apiError.getDetails() == null ? "" : apiError.getDetails()) + "\n\n";
    }
}
