package com.despi.jwtrestserver.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

	private HttpStatus httpStatus;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable t) {
		super(message, t);
	}

	public ApplicationException(String messageTemplate, Object... args) {
		super(String.format(messageTemplate, args));
	}

	public ApplicationException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public ApplicationException(String message, Throwable t, HttpStatus httpStatus) {
		super(message, t);
		this.httpStatus = httpStatus;
	}

	public ApplicationException(String messageTemplate, HttpStatus httpStatus, Object... args) {
		super(String.format(messageTemplate, args));
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static void assertEntityNotNull(Object entity, Object id, Class clazz) {
		if (entity != null) {
			return;
		}
		throw entityNotFound(id, clazz);
	}

	public static ApplicationException entityNotFound(Object id) {
		return new ApplicationException(String.format("Could not find entity, id: %s", id), HttpStatus.NOT_FOUND);
	}

	public static ApplicationException entityNotFound(Object id, Class clazz) {
		return new ApplicationException(String.format("Could not find entity, id: %s, class: %s", id, clazz.toString()), HttpStatus.NOT_FOUND);
	}

	public static ApplicationException unauthorized() {
		return new ApplicationException("You are not authorize to perform this operation", HttpStatus.UNAUTHORIZED);
	}

	public static void assertNotNull(Object object, String message, Object ...args) {
		if (object == null) {
			throw new ApplicationException(String.format(message, args), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public static void assertTrue(boolean expression, String messageTemplate, Object... args) {
		if (!expression) {
			throw new ApplicationException(messageTemplate, HttpStatus.NOT_ACCEPTABLE, args);
		}
	}
}
