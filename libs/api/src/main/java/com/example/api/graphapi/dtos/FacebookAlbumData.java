package com.example.api.graphapi.dtos;

public class FacebookAlbumData {
	private String id;
	private String name;
	private int count;
	private FacebookAlbumCoverPhoto cover_photo;
	private FacebookPicture picture;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public FacebookAlbumCoverPhoto getCoverPhoto() {
		return cover_photo;
	}

	public int getCount() {
		return count;
	}

	public FacebookPicture getPicture() {
		return picture;
	}
}
