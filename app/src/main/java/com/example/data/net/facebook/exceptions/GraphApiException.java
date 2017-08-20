package com.example.data.net.facebook.exceptions;

import com.facebook.FacebookRequestError;
import com.facebook.GraphResponse;

public class GraphApiException extends RuntimeException {
	private final GraphResponse originalGraphResponse;
	private final FacebookRequestError facebookRequestError;

	public GraphApiException(GraphResponse originalGraphResponse) {
		super(originalGraphResponse.getError().getException());

		this.originalGraphResponse = originalGraphResponse;
		this.facebookRequestError = originalGraphResponse.getError();
	}

	public GraphResponse getOriginalGraphResponse() {
		return originalGraphResponse;
	}

	public FacebookRequestError getFacebookRequestError() {
		return facebookRequestError;
	}
}
