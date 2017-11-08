package com.despi.jwtrestserver.controller;

import com.despi.jwtrestserver.exception.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity handleBadInput(ConstraintViolationException ex, WebRequest request) {
		LinkedList<Map<String, String>> errors = new LinkedList<>();
		for (ConstraintViolation violation : ex.getConstraintViolations()) {
			HashMap<String, String> map = new HashMap<>();

			ConstraintDescriptor descriptor = violation.getConstraintDescriptor();
			Map<String, Object> attributes = descriptor.getAttributes();
			attributes.entrySet().stream()
					.filter(entry -> entry.getValue() instanceof String || entry.getValue() instanceof Number || entry.getValue() instanceof Boolean)
					.forEach(entry -> {
						map.put(entry.getKey(), entry.getValue().toString());
					});

			String path = violation.getPropertyPath().toString();
			path = path.substring(path.indexOf(".") + 1);
			map.put("path", path);

			map.put("description", violation.getMessage());

			errors.add(map);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(ex, errors, headers, HttpStatus.NOT_ACCEPTABLE, request);
	}

	@ExceptionHandler(value = {ApplicationException.class})
	public ResponseEntity handleApplicationException(ApplicationException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HashMap<String, Object> body = new HashMap() {{
			put("error", ex.getMessage());
		}};
		return handleExceptionInternal(ex, body, headers, ex.getHttpStatus(), request);
	}
}