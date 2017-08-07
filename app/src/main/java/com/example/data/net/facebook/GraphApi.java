package com.example.data.net.facebook;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.data.net.facebook.dtos.FacebookAlbum;
import com.example.data.net.facebook.dtos.FacebookPhoto;
import com.example.data.net.facebook.dtos.FacebookUser;

import java.util.List;

import rx.Observable;

public interface GraphApi {
    Observable<FacebookUser> me(@NonNull final String... fields);

    Observable<FacebookAlbum> getAlbums();

    Observable<List<FacebookPhoto>> getAlbumPhotos(@NonNull final String albumId);

    Observable<List<FacebookPhoto>> getAlbumPhotos(@NonNull final String albumId, int limit);

    Observable<Bitmap> downloadLargestPhoto(@NonNull String photoId);
}
