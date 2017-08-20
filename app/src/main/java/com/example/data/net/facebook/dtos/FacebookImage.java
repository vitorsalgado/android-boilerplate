package com.example.data.net.facebook.dtos;

import java.io.Serializable;

public class FacebookImage implements Serializable {
	private int height;
	private int width;
	private String source;

	public FacebookImage(int height, int width, String source) {
		this.height = height;
		this.width = width;
		this.source = source;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getSource() {
		return source;
	}
}
