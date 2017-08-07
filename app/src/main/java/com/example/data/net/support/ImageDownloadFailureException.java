package com.example.data.net.support;

public class ImageDownloadFailureException extends RuntimeException {
    public ImageDownloadFailureException(Exception exception) {
        super(exception);
    }
}