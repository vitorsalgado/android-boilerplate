package com.example.data.net.facebook.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FacebookPictureUrl implements Serializable {
	private String url;

	@SerializedName("is_silhouette")
	private String isSilhouette;

	public FacebookPictureUrl(String url, String isSilhouette) {
		this.url = url;
		this.isSilhouette = isSilhouette;
	}

	public String getUrl() {
		return url;
	}

	public String getIsSilhouette() {
		return isSilhouette;
	}
}
