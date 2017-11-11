package com.despi.jwtrestserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface Dto {
	@JsonIgnore
	String getDtoObjectName();
	void assertIsValid();
	boolean isValid();
	@JsonIgnore
	List<String> getErrors();
}
