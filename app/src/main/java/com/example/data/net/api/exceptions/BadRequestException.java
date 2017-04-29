package com.example.data.net.api.exceptions;

import com.example.data.net.api.dtos.ApiError;

public class BadRequestException extends BaseHttpException {
    public BadRequestException(ApiError apiError) {
        super(apiError);
    }
}
