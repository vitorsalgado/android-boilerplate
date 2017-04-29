package com.example.data.net.facebook.dtos;

import java.io.Serializable;
import java.util.List;

public class FacebookAlbumPhotos implements Serializable {
    private List<FacebookPhoto> data;

    public List<FacebookPhoto> getData() {
        return data;
    }
}
