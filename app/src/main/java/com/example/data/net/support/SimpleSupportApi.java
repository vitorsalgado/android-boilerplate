package com.example.data.net.support;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SimpleSupportApi implements SupportApi {
    private static SupportApi instance;

    public static SupportApi getInstance() {
        if (instance == null) {
            instance = new SimpleSupportApi();
        }

        return instance;
    }

    public Bitmap downloadImage(@NonNull String source) {
        try {
            URL url = new URL(source);
            InputStream in = (InputStream) url.getContent();

            return BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            throw new ImageDownloadFailureException(e);
        }
    }
}
