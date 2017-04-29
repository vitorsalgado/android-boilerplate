package com.example.data.net.facebook.dtos;

import java.io.Serializable;
import java.util.List;

public class FacebookPhoto implements Serializable {
    private String id;
    private List<FacebookImage> images;

    public FacebookPhoto(String id, List<FacebookImage> images) {
        this.id = id;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public List<FacebookImage> getImages() {
        return images;
    }

    public FacebookImage getLargest() {
        return getImages().get(0);
    }

    public FacebookImage getSmallest() {
        return getImages().get(getImages().size() - 1);
    }
}
