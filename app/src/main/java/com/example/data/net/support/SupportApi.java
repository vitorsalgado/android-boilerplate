package com.example.data.net.support;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface SupportApi {
    Bitmap downloadImage(@NonNull String source);
}
