package com.example.api.facebook.graphapi.dtos;

import com.google.gson.annotations.SerializedName;

public class FacebookUser {
	private String id;

	private String name;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("last_name")
	private String lastName;

	private String link;

	private String username;

	private String gender;

	private String email;

	private String birthday;

	private String bio;

	private FacebookPicture picture;

	private FacebookAlbum albums;

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLink() {
		return link;
	}

	public String getUsername() {
		return username;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBio() {
		return bio;
	}

	public FacebookPicture getPicture() {
		return picture;
	}

	public FacebookAlbum getAlbums() {
		return albums;
	}
}
