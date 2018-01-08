package com.example.graphapi.dtos;

public class FacebookImage {
	private final int height;
	private final int width;
	private final String source;

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
