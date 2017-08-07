package com.example.data.net.api;

import com.example.data.net.api.dtos.ApiError;
import com.example.data.net.api.exceptions.BadRequestException;
import com.example.data.net.api.exceptions.UnauthorizedException;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ExceptionTransformerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (response.isSuccessful()) {
            return response;
        }

        if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
            throw new BadRequestException(parseBodyError(response.body().string(), response.message()));
        }

        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            throw new UnauthorizedException(parseBodyError(response.body().string(), response.message()));
        }

        return response;
    }

    private static ApiError parseBodyError(String bodyContent, String defaultMessage) {
        ApiError error;

        try {
            error = new Gson().fromJson(bodyContent, ApiError.class);
        } catch (Exception ignored) {
            return new ApiError(defaultMessage);
        }

        if (error == null) {
            error = new ApiError(defaultMessage);
        }

        return error;
    }
}
