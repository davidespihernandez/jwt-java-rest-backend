package com.despi.jwtrestserver.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}