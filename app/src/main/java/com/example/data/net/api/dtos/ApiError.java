package com.example.data.net.api.dtos;

import java.util.List;

public class ApiError {
	private String message;
	private String type;
	private String code;
	private String trace_id;
	private List<Error> errors;

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getTrace_id() {
		return trace_id;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public ApiError(String message) {
		this.message = message;
	}

	class Error {
		private String field;
		private String message;

		public String getField() {
			return field;
		}

		public String getMessage() {
			return message;
		}
	}
}
