package com.despi.jwtrestserver.domain.jsonb;

public class ApplicationException extends RuntimeException {

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable t) {
		super(message, t);
	}

	public ApplicationException(String messageTemplate, Object... args) {
		super(String.format(messageTemplate, args));
	}

	public static void assertEntityNotNull(Object entity, Object id, Class clazz) {
		if (entity != null) {
			return;
		}
		throw entityNotFound(id, clazz);
	}

	public static ApplicationException entityNotFound(Object id) {
		return new ApplicationException(String.format("Could not find entity, id: %d", id));
	}

	public static ApplicationException entityNotFound(Object id, Class clazz) {
		return new ApplicationException(String.format("Could not find entity, id: %d, class: %s", id, clazz.toString()));
	}

	public static ApplicationException unauthorized() {
		return new ApplicationException("You are not authorize to perform this operation");
	}

	public static void assertNotNull(Object object, String message, Object ...args) {
		if (object == null) {
			throw new ApplicationException(String.format(message, args));
		}
	}

	public static void assertTrue(boolean expression, String messageTemplate, Object... args) {
		if (!expression) {
			throw new ApplicationException(messageTemplate, args);
		}
	}
}
