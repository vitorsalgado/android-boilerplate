package com.example.data.net.facebook;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;

import com.example.AppDefaults;
import com.example.data.net.facebook.dtos.FacebookAlbum;
import com.example.data.net.facebook.dtos.FacebookAlbumPhotos;
import com.example.data.net.facebook.dtos.FacebookPhoto;
import com.example.data.net.facebook.dtos.FacebookUser;
import com.example.data.net.facebook.exceptions.GraphApiException;
import com.example.data.net.facebook.exceptions.GraphApiTransientException;
import com.example.data.net.facebook.token.FBAccessTokenProvider;
import com.example.data.net.facebook.utils.FbUtils;
import com.example.data.net.facebook.utils.Fields;
import com.example.data.net.support.SupportApi;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class FbGraphApi implements GraphApi {
    private static final String TAG = "FbGraphApi";
    private static GraphApi instance;

    private final String FIELDS = "fields";
    private final String RESPONSE_LOCALE = "en_US";
    private final int MAX_RETRY_ATTEMPTS = 2;
    private final long ATTEMPT_DELAY_IN_SECONDS = 2;

    private final SupportApi mSupportApi;
    private final FBAccessTokenProvider mAccessTokenProvider;

    public static GraphApi getInstance(@NonNull SupportApi supportApi, @NonNull FBAccessTokenProvider accessTokenProvider) {
        if (instance == null) {
            instance = new FbGraphApi(supportApi, accessTokenProvider);
        }

        return instance;
    }

    public FbGraphApi(@NonNull SupportApi supportApi, @NonNull FBAccessTokenProvider accessTokenProvider) {
        mSupportApi = supportApi;
        mAccessTokenProvider = accessTokenProvider;
    }

    @Override
    public Observable<FacebookUser> me(@NonNull final String... fields) {
        return Observable
                .defer(() -> {
                    GraphRequest request = GraphRequest.newMeRequest(mAccessTokenProvider.getCurrent(), (object, response) -> { });

                    request.getParameters().putString(FIELDS, FbUtils.convert(fields));
                    request.getParameters().putString("locale", RESPONSE_LOCALE);

                    GraphResponse graphResponse = request.executeAndWait();

                    if (graphResponse.getError() != null) {
                        if (graphResponse.getError().getCategory() == FacebookRequestError.Category.TRANSIENT) {
                            throw new GraphApiTransientException(graphResponse);
                        }

                        throw new GraphApiException(graphResponse);
                    }

                    return Observable.just(new Gson()
                            .fromJson(String.valueOf(graphResponse.getJSONObject()), FacebookUser.class));
                })
                .retryWhen(retryWhenTransient());
    }

    @Override
    public Observable<FacebookAlbum> getAlbums() {
        return Observable
                .defer(() -> {
                    GraphRequest request = GraphRequest.newMeRequest(mAccessTokenProvider.getCurrent(), (object, response) -> { });
                    Bundle parameters = new Bundle();

                    parameters.putString(FIELDS, "albums.limit(15){count,name,cover_photo,picture}");
                    request.setParameters(parameters);

                    GraphResponse graphResponse = request.executeAndWait();

                    if (graphResponse.getError() != null) {
                        if (graphResponse.getError().getCategory() == FacebookRequestError.Category.TRANSIENT) {
                            throw new GraphApiTransientException(graphResponse);
                        }

                        throw new GraphApiException(graphResponse);
                    }

                    FacebookUser user = new Gson().fromJson(String.valueOf(graphResponse.getJSONObject()), FacebookUser.class);

                    return Observable.just(user.getAlbums());
                })
                .retryWhen(retryWhenTransient());
    }

    @Override
    public Observable<List<FacebookPhoto>> getAlbumPhotos(@NonNull final String albumId) {
        return getAlbumPhotos(albumId, 100);
    }

    @Override
    public Observable<List<FacebookPhoto>> getAlbumPhotos(@NonNull final String albumId, int limit) {
        return Observable
                .defer(() -> {
                    String graphPath = String.format("%s/photos", albumId);
                    GraphRequest request = GraphRequest.newGraphPathRequest(mAccessTokenProvider.getCurrent(), graphPath, response -> { });

                    request.getParameters().putString(FIELDS, FbUtils.convert(new String[]{Fields.IMAGES}));
                    request.getParameters().putInt("limit", limit);
                    request.getParameters().putString("locale", RESPONSE_LOCALE);

                    GraphResponse graphResponse = request.executeAndWait();

                    if (graphResponse.getError() != null) {
                        if (graphResponse.getError().getCategory() == FacebookRequestError.Category.TRANSIENT) {
                            throw new GraphApiTransientException(graphResponse);
                        }

                        throw new GraphApiException(graphResponse);
                    }

                    FacebookAlbumPhotos facebookAlbumPhotos = new Gson()
                            .fromJson(String.valueOf(graphResponse.getJSONObject()), FacebookAlbumPhotos.class);

                    List<FacebookPhoto> facebookPhotos = new ArrayList<>();

                    if (facebookAlbumPhotos != null && facebookAlbumPhotos.getData() != null && !facebookAlbumPhotos.getData().isEmpty()) {
                        for (FacebookPhoto facebookPhoto : facebookAlbumPhotos.getData()) {
                            Collections.sort(facebookPhoto.getImages(),
                                    (img1, img2) -> Integer.valueOf(img2.getWidth()).compareTo(img1.getWidth()));

                            facebookPhotos.add(facebookPhoto);
                        }
                    }

                    return Observable.just(facebookPhotos);
                })
                .retryWhen(retryWhenTransient());
    }

    @Override
    public Observable<Bitmap> downloadLargestPhoto(@NonNull String photoId) {
        return Observable
                .defer(() -> {
                    Log.d(TAG, photoId);
                    String graphPath = String.format("/%s", photoId);
                    GraphRequest request = GraphRequest.newGraphPathRequest(mAccessTokenProvider.getCurrent(), graphPath, response -> { });

                    request.getParameters().putString(FIELDS, FbUtils.convert(new String[]{Fields.IMAGES}));

                    GraphResponse graphResponse = request.executeAndWait();

                    if (graphResponse.getError() != null) {
                        if (graphResponse.getError().getCategory() == FacebookRequestError.Category.TRANSIENT) {
                            throw new GraphApiTransientException(graphResponse);
                        }

                        throw new GraphApiException(graphResponse);
                    }

                    FacebookPhoto facebookPhoto = new Gson().fromJson(String.valueOf(graphResponse.getJSONObject()), FacebookPhoto.class);

                    Collections.sort(facebookPhoto.getImages(),
                            (img1, img2) -> Integer.valueOf(img2.getWidth()).compareTo(img1.getWidth()));

                    return Observable.just(facebookPhoto);
                })
                .retryWhen(retryWhenTransient())
                .map(facebookPhoto -> mSupportApi.downloadImage(facebookPhoto.getLargest().getSource()));
    }

    @NonNull
    private Func1<Observable<? extends Throwable>, Observable<?>> retryWhenTransient() {
        return observable -> observable
                .doOnError(throwable -> Log.e(TAG, throwable.getMessage(), throwable))
                .zipWith(Observable.range(1, 3), new Func2<Throwable, Integer, Pair<Throwable, Integer>>() {
                    @Override
                    public Pair<Throwable, Integer> call(Throwable throwable, Integer integer) {
                        return Pair.create(throwable, integer);
                    }
                }).flatMap((Func1<Pair<Throwable, Integer>, Observable<?>>) pair -> {
                    if (!(pair.first instanceof GraphApiTransientException)) {
                        return Observable.error(pair.first);
                    }

                    if (pair.second == MAX_RETRY_ATTEMPTS) {
                        return Observable.error(pair.first);
                    }

                    return Observable.timer(ATTEMPT_DELAY_IN_SECONDS, TimeUnit.SECONDS);
                });
    }

}
