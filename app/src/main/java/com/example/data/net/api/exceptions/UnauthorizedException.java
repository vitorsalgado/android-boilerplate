package com.example.data.net.api.exceptions;

import com.example.data.net.api.dtos.ApiError;

public class UnauthorizedException extends BaseHttpException {
    public UnauthorizedException(ApiError apiError) {
        super(apiError);
    }

    public UnauthorizedException() {
        super();
    }
}
