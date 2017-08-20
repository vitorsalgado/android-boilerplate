package com.example.data.net.facebook.exceptions;

import com.facebook.GraphResponse;

public class GraphApiTransientException extends GraphApiException {
	public GraphApiTransientException(GraphResponse originalGraphResponse) {
		super(originalGraphResponse);
	}
}
