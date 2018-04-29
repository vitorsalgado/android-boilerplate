package com.example.api.graphapi.dtos;

import java.util.List;

public class FacebookPhoto {
	private final String id;
	private final List<FacebookImage> images;

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
