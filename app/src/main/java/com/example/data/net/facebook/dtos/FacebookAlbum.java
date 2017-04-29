package com.example.data.net.facebook.dtos;

import java.io.Serializable;
import java.util.List;

public class FacebookAlbum implements Serializable {
    private List<FacebookAlbumData> data;

    public List<FacebookAlbumData> getData() {
        return data;
    }
}
