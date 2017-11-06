package com.despi.jwtrestserver.controller;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.Dto;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.mapper.JsonResponse;
import com.despi.jwtrestserver.mapper.UserMapper;
import com.despi.jwtrestserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;

	@Autowired
	public UserController(UserService userService
			, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/{id}")
	public Object getUser(@PathVariable Long id) {
		User user = userService.getById(id);
		//todo: add error when wrong id, error method in JsonResponse conforming the Ember RESTAdapter structure
		return JsonResponse.toJsonMap(userMapper.convertToDto(user));
	}

	@GetMapping
	public Object getAllUsers() {
		List<Dto> dtos =  userService.getAllUsers()
				.stream()
				.map(userMapper::convertToDto)
				.collect(Collectors.toList());
		return JsonResponse.toJsonMap(dtos);
	}

	@PostMapping
	public Object createOrUpdateUser(@RequestBody UserDto userDto) {
		User user = userService.createOrUpdateUser(userDto);
		return JsonResponse.toJsonMap(userMapper.convertToDto(user));
	}
}
