package com.example.data.net.api;

import com.example.App;
import com.example.AppDefaults;
import com.example.utils.AppUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MobileDefaultsInterceptor implements Interceptor {
    private static final String VERSION_NAME = AppUtils.getVersionName(App.getContext());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original
                .newBuilder()
                .header("channel", AppDefaults.Channel)
                .header("app_version", VERSION_NAME)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
