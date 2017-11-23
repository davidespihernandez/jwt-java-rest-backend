package com.despi.jwtrestserver.mapper;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserMapper {

	private final ModelMapper modelMapper;

	@Autowired
	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setPassword(null);
		return userDto;
	}
}
