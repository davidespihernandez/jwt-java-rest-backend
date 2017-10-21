package com.despi.jwtrestserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Dto {
	@JsonIgnore
	String getDtoObjectName();
}
