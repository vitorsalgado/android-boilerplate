package com.example.data.net.facebook.dtos;

import java.io.Serializable;

public class FacebookPicture implements Serializable {
	private String id;
	private String picture;
	private FacebookPictureUrl data;

	public FacebookPictureUrl getData() {
		return data;
	}

	public String getId() {
		return id;
	}

	public String getPicture() {
		return picture;
	}
}
