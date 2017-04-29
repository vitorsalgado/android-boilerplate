package com.example.data.net.api.dtos;

public class ApiError {
    private String message;
    private String code;
    private String details;
    private String traceID;

    public ApiError(String message, String code, String details, String traceID) {
        this.message = message;
        this.code = code;
        this.details = details;
        this.traceID = traceID;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }

    public String getTraceID() {
        return traceID;
    }
}
